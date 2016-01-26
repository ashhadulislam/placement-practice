package sem2graph;

import java.io.*;
import java.util.*;

public class BipartiteGraph {

	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Graph<Integer> graph=new Graph<Integer>(false);
        
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(2,4);
        graph.addEdge(3,4);
        
        
        
        boolean isBipartite=DFSForBipartiteCheck(graph);
        System.out.println("Graph is " + isBipartite);
        
        
        
    }
    
    public static boolean DFSForBipartiteCheck(Graph graph){
        
        boolean isBipartite=false;
        
        
        Vertex<Integer> startVertex=(Vertex<Integer>) graph.getAllVertices().iterator().next();
             
        int color=-1;
        isBipartite=DFSIterate(startVertex,color);
        
        return isBipartite;
        
    }
    
    public static boolean DFSIterate(Vertex<Integer> vert,int parentColor){
        boolean isBipartite=true;
        int currentColor=vert.getColor();
        if(currentColor==-1){//current vertex hasn't been colored
            currentColor=(parentColor+1)%2;
            vert.setColor(currentColor);
            ArrayList<Vertex<Integer>> adjVert=new ArrayList<Vertex<Integer>>(vert.getAdjacentVertices());
            for(Vertex<Integer> v:adjVert){
                isBipartite=DFSIterate(v,currentColor);
                if(!isBipartite)return isBipartite;
            }
            
        }
        else{
            if(currentColor==parentColor){
                return false;
            }
            else{
                return true;
            }
            
        }
            
            
        
        
        return isBipartite;
    }
    
    
    
    static class Graph<T>{
        List<Edge<T>> allEdges;
        Map<Long,Vertex<T>> allVertices;
        boolean isDirected=false;
        
        public Graph(boolean isDirected){
            this.isDirected=isDirected;
            allEdges=new ArrayList<Edge<T>>();
            allVertices=new HashMap<Long,Vertex<T>>();
        }
        
        public Collection<Vertex<T>> getAllVertices(){
            return allVertices.values();
        }
        
        public void addEdge(long v1,long v2){
            addEdge(v1,v2,0);
        }
        public void addEdge(long v1,long v2,int weight){
            //1st add the vertices
            //to the allvertices hash map
            Vertex<T> v1Val=null;
            if(allVertices.containsKey(v1)){
                v1Val=allVertices.get(v1);
            }
            else{
                v1Val=new Vertex(v1);
                allVertices.put(v1,v1Val);
            }
            
            Vertex<T> v2Val=null;
            if(allVertices.containsKey(v2)){
                v2Val=allVertices.get(v2);
            }
            else{
                v2Val=new Vertex(v2);
                allVertices.put(v2,v2Val);
            }
            
            //now form the edge
            
            Edge edg=new Edge(v1Val,v2Val,weight,isDirected);            
            allEdges.add(edg);
            
            //now add a to adjacentV and incidentE
            v1Val.addAdjacent(v2Val);
            v1Val.addIncident(edg);
            if(!isDirected){
                v2Val.addAdjacent(v1Val);
                v2Val.addIncident(edg);
            }
            
                        
            
        }
            
        
        
    }
    
    static class Vertex<T>{
        long id;
        T data;
        int color;
        private List<Edge<T>> incidentEdges;
        private List<Vertex<T>> adjacentVertices;
        
        public Vertex(long id){
            this.id=id;
            incidentEdges=new ArrayList<Edge<T>>();
            adjacentVertices=new ArrayList<Vertex<T>>();
            color=-1;
        }
        public List<Vertex<T>> getAdjacentVertices(){
            return adjacentVertices; 
        }
        public void addAdjacent(Vertex<T> v){
            adjacentVertices.add(v);
        }
        public void addIncident(Edge<T> edg){
            incidentEdges.add(edg);
        }
        public void setColor(int color){
            this.color=color;
        }
        public int getColor(){
            return this.color;
        }
        
        
    }
    static class Edge<T>{
        boolean isDirected=false;
        Vertex<T> vertex1;
        Vertex<T> vertex2;
        int weight;
        public Edge(Vertex<T> v1,Vertex<T> v2,int weight,boolean isDirected){
            this.vertex1=vertex1;
            this.vertex2=vertex2;
            this.weight=weight;
            this.isDirected=isDirected;
        }
    }
 
	
	
}
