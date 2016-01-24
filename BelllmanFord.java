package mspeace;

import java.util.*;

class BellmanFord{

	class NegWeightCycleException extends RuntimeException{}
				
	public static void main(String[] args){

		Graph<Integer> graph=new Graph(true);
		
//		graph.addEdge(0, 3, 8);
//        graph.addEdge(0, 1, 4);
//        graph.addEdge(0, 2, 5);
//        graph.addEdge(1, 2, -3);
//        graph.addEdge(2, 4, 4);
//        graph.addEdge(3, 4, 2);
//        graph.addEdge(4, 3, 1);
		
		graph.addEdge(0, 1, -1);
		graph.addEdge(0, 2, 4);
		graph.addEdge(3, 2, 5);
		graph.addEdge(1, 3, 2);
		graph.addEdge(3, 1, 1);
		graph.addEdge(4, 3, -3);
		graph.addEdge(1, 4, 2);
		graph.addEdge(1, 2, 3);
		
		
        

		BellmanFord BFord=new BellmanFord();
		Map<Vertex<Integer>,Vertex<Integer>> parent=
			new HashMap<Vertex<Integer>,Vertex<Integer>>();
		Map<Vertex<Integer>,Integer> distance=
			new HashMap<Vertex<Integer>,Integer>();
		Vertex<Integer> srcV=graph.getAllVertices().iterator().next();
		BFord.applyBellmanFord(graph,parent,distance,srcV);

		for (Vertex<Integer> vertex: distance.keySet()){            
				int value = distance.get(vertex);
	            System.out.println("distance to "+vertex.getID() + " is " + value);  
		} 

		
	}

	void applyBellmanFord(Graph<Integer> G,Map<Vertex<Integer>,Vertex<Integer>> parent,
			Map<Vertex<Integer>,Integer> distance,Vertex<Integer> srcV)
	{
		for(Vertex<Integer> vert:G.getAllVertices()){
			parent.put(vert,null);
			distance.put(vert,Integer.MAX_VALUE);


		}//end of setting default values		
		distance.put(srcV, 0);

		ArrayList<Edge<Integer>> allEdges=new ArrayList<Edge<Integer>>( G.getAllEdges());
		int numOfEdges=G.getAllVertices().size();
		for(int i=0;i<numOfEdges-1;i++){
			for(Edge<Integer> edg:allEdges){
				Vertex<Integer> v1=edg.getSourceVertex();
				Vertex<Integer> v2=edg.getDestVertex();
				//now the relaxing work				
				if(distance.get(v1)<Integer.MAX_VALUE)
					relax(v1,v2,edg,parent,distance);
	


			}//end of iterating through all edges

		}//end of V-1 iterations

		//check for negative cycle
		for(Edge<Integer> edg:allEdges){
			Vertex<Integer> v1=edg.getSourceVertex();
			Vertex<Integer> v2=edg.getDestVertex();			
			if(distance.get(v2)>
				(distance.get(v1)+edg.getWeight())){
				throw new NegWeightCycleException();
			}
		}//end of iterating through all edges
	}//end of applyBEllmanFord method

	
	void relax(Vertex<Integer> src, Vertex<Integer>  dest, 
			Edge<Integer> edg,
			Map<Vertex<Integer>,Vertex<Integer>> parent,
			Map<Vertex<Integer>,Integer> distance
	){
		if(distance.get(dest)>(distance.get(src)+edg.getWeight())){
			distance.put(dest,(distance.get(src)+edg.getWeight()));
			parent.put(dest,src);
		}

	}//end of relax method


}//end of BellamnFord class

class Edge<T>{
	boolean isDirected=false;
	Vertex<T> vertex1;
	Vertex<T> vertex2;
	int weight;

	Edge(Vertex<T> v1, Vertex<T> v2,int weight,boolean isDirected)
	{
		this.vertex1=v1;
		this.vertex2=v2;
		this.weight=weight;
		this.isDirected=isDirected;
	}//end of constructor
	public Vertex<T> getSourceVertex(){
		return vertex1;
	}
	public Vertex<T> getDestVertex(){
		return vertex2;
	}
	int getWeight(){
		return weight;
	}




}//end of class

class Vertex<T>{
	long id;
	T data;
	private List<Vertex<T>> adjacentVertices;
	private List<Edge<T>> incidentEdges;
	
	Vertex(long id){
		this.id=id;
		adjacentVertices=new ArrayList<Vertex<T>>();
		incidentEdges=new ArrayList<Edge<T>>();
	}
	public void addAdjacentVertex(Vertex<T> v){
		adjacentVertices.add(v);
	}	
	public void addIncidentEdge(Edge<T> e){
		incidentEdges.add(e);
	}

	public long getID(){
		return id;
	}
}//end of Vertex class

class Graph<T>{
	List<Edge<T>> allEdges;
	Map<Long,Vertex<T>> allVertices;
	boolean isDirected=false;

	Graph(boolean isDirected){
		this.isDirected=isDirected;
		allEdges=new ArrayList<Edge<T>>();
		allVertices=new HashMap<Long,Vertex<T>>();
	}//end of constructor


	public List<Edge<T>> getAllEdges(){
		return allEdges;	
	}//end of getAllEdges

	
	public Collection<Vertex<T>> getAllVertices(){
		return allVertices.values();
	}


	public void addEdge(long v1,long v2,int wt){
		Vertex<T> vertex1=null;
		Vertex<T> vertex2=null;
		if(allVertices.containsKey(v1)){
			vertex1=allVertices.get(v1);
		}
		else{
			vertex1=new Vertex<T>(v1);
			allVertices.put(v1,vertex1);
		}

		if(allVertices.containsKey(v2)){
			vertex2=allVertices.get(v2);
		}
		else{
			vertex2=new Vertex<T>(v2);
			allVertices.put(v2,vertex2);
		}
		Edge<T> edge=new Edge<T>(vertex1,vertex2,wt,isDirected);
		allEdges.add(edge);
		vertex1.addAdjacentVertex(vertex2);
		vertex1.addIncidentEdge(edge);
		if(!isDirected){
			vertex2.addAdjacentVertex(vertex1);
			vertex2.addIncidentEdge(edge);
		}
	}//end of adding edge
	
	

}//end of Graph class