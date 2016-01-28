package mspeace;

import java.util.*;

public class Bridge<T> {
	public static void main(String[] args) {
        Graph graph=new Graph<Integer>(false);
        
        //graph.addEdge(0,1);
        //graph.addEdge(0,2);
        
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(0,3);
        graph.addEdge(3,4);
        
        Bridge<Integer> bridge=new Bridge();
        
        Set<Edge<Integer>> bridgeEdgeSet=new HashSet<Edge<Integer>>();
        bridgeEdgeSet=bridge.findBridges(graph);        
        
        //bridgeEdgeSet.forEach(point->System.out.println(point.getSourceV().id+" "+point.getDestV().id));
        
        for(Edge e:bridgeEdgeSet){
            System.out.print(e.getSourceV().id+" ");
            System.out.println(e.getDestV().id);
                               
        }
    }
    private int time;
    
    public Set<Edge<T>> findBridges(Graph<T> graph){
        time=0;
        
        Set<Edge<T>> bridgeEdgeSet=new HashSet<Edge<T>>();
        Set<Vertex<T>> visitedV=new HashSet<Vertex<T>>();
        
        Vertex<T> startVertex=graph.getAllVertices().iterator().next();
        
        
        Map<Vertex<T>,Integer> visitedTime=new HashMap<Vertex<T>,Integer>();
        Map<Vertex<T>,Integer> lowTime=new HashMap<Vertex<T>,Integer>();
        Map<Vertex<T>,Vertex<T>> parent=new HashMap<Vertex<T>,Vertex<T>>();
        
        DFS(startVertex,visitedV,parent,visitedTime,lowTime,bridgeEdgeSet);
        
        
        return bridgeEdgeSet;
    }
    
    public void DFS(Vertex<T> visitingVertex,Set<Vertex<T>>visitedV,
               Map<Vertex<T>,Vertex<T>>parent,Map<Vertex<T>,Integer>visitedTime,
               Map<Vertex<T>,Integer>lowTime,Set<Edge<T>> bridgeEdgeSet){
        
        Vertex<T> currV=visitingVertex;
        visitedTime.put(currV,time);
        lowTime.put(currV,time);
        visitedV.add(currV);
        time++;
        
        List<Vertex<T>> allAdjV=currV.getAllAdjacentVertices();
        
        for(Vertex<T>adj:allAdjV){//iterating for each adjacent vertex
            System.out.println("currV = "+currV.id);
            System.out.println("adj = "+adj.id);
            //check if adjacent is parent itself
            if(adj.equals(parent.get(currV))){
                continue;
            }
            
            //if adj has not been visitedd
            
            if(!visitedV.contains(adj)){
                parent.put(adj,currV);
                DFS(adj,visitedV,parent,visitedTime,lowTime,bridgeEdgeSet);  
                System.out.print("vis of "+currV.id+"= "+visitedTime.get(currV)+"<");
                System.out.println("low of "+adj.id+" = " + lowTime.get(adj));
                //now that all children have been visited
                if(visitedTime.get(currV)<lowTime.get(adj)){
                    bridgeEdgeSet.add(new Edge(currV,adj));
                }
                //then update lowtime of currV
                System.out.println("min="+Math.min(visitedTime.get(currV),lowTime.get(adj)));
                lowTime.put(currV,Math.min(lowTime.get(currV),lowTime.get(adj)));
                System.out.println("low of "+currV.id+" = " + lowTime.get(currV));   
                
                
                
                
            }//end of action for not visited adjacent vertex
            
            else{//not parent but has been visited
                //update between my low time and adjacent visited time
                lowTime.put(currV,Math.min(lowTime.get(currV),visitedTime.get(adj)));
                
            }//end of action for visited vertices
            
            
            
            
        }//end of for loop
        
        
        
    }
    
    static class Graph<T>{
        Map<Long,Vertex<T>> allVertices;
        List<Edge<T>> allEdges;
        boolean isDirected=false;
        Graph(boolean isDirected){
            this.isDirected=isDirected;
            allVertices=new HashMap<Long,Vertex<T>>();
            allEdges=new ArrayList<Edge<T>>();        
        }
        
        public List<Edge<T>> getAllEdges(){
            return allEdges;
        }
        
        public Collection<Vertex<T>> getAllVertices(){
            return allVertices.values();
        }
        
        public void addEdge(long v1,long v2){
            addEdge(v1,v2,0);
        }
            
        
        
        public void addEdge(long v1,long v2,int weight){
            Vertex<T> vertex1=null;
            Vertex<T> vertex2=null;
            
            if(allVertices.containsKey(v1)){
                vertex1=allVertices.get(v1);
                
            }else{
                vertex1=new Vertex(v1);
                allVertices.put(v1,vertex1);
            }
            
            
            
            if(allVertices.containsKey(v2)){
                vertex2=allVertices.get(v2);
                
            }else{
                vertex2=new Vertex(v2);
                allVertices.put(v2,vertex2);
            }
            
            Edge<T> edg=new Edge<T>(vertex1,vertex2,weight,isDirected);
            
            allEdges.add(edg);
            
            vertex1.addAdjacentVertex(vertex2);
            vertex1.addIncidentEdge(edg);
            
            if(!isDirected){
                
                vertex2.addAdjacentVertex(vertex1);
                vertex2.addIncidentEdge(edg);        
            }
            
            
        }//end of add edge
        
        
        
    }

    static class Vertex<T>{
        List<Vertex<T>> adjacentVertices;
        List<Edge<T>> incidentEdges;
        long id;
        T data;
        Vertex(Long id){
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
        
        public List<Vertex<T>> getAllAdjacentVertices(){
            return adjacentVertices;
        }
        
        
        
        
    }

    static class Edge<T>{
        Vertex<T> vertex1;
        Vertex<T> vertex2;
        boolean isDirected=false;
        int weight;
        
        
        
        Edge(Vertex<T> v1,Vertex<T> v2){
            this.vertex1=v1;
            this.vertex2=v2;
            this.weight=0;
            this.isDirected=false;
        }
        
        
        Edge(Vertex<T> v1,Vertex<T> v2,int weight,boolean isDirected){
            this.vertex1=v1;
            this.vertex2=v2;
            this.weight=weight;
            this.isDirected=isDirected;
            
        }
        
        public Vertex<T> getSourceVertex(){
            return vertex1;
        }
        public Vertex<T> getDestVertex(){
            return vertex2;
        }
        public int getEdgeWeight(){
            return weight;
        }
        public Vertex<T> getSourceV(){
            return vertex1;
        }
        public Vertex<T> getDestV(){
            return vertex2;
        }
        


    }

    
    
}

