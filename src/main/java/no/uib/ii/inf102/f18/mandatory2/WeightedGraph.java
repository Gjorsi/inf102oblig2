package no.uib.ii.inf102.f18.mandatory2;

import java.util.HashMap;

public class WeightedGraph {

    private HashMap<Integer, Vertex> vertices;
    
    public WeightedGraph(int n) {
        this.vertices = new HashMap<>();
        
        for (int i=0; i<n; i++) {
            this.vertices.put(i, new Vertex(i, Long.MAX_VALUE/2, Long.MAX_VALUE/2));
        }
    }
    
    public void addEdge(int u, int v, int weight) {
        vertices.get(u).nbrs.add(new Edge(v,weight, false));
        vertices.get(v).nbrs.add(new Edge(u,weight, false));
    }

    public Vertex getVertex (int x) {
        return vertices.get(x);
    }
    
    public void addFlight(int u, int v) {
        vertices.get(u).nbrs.add(new Edge(v, 0, true));
    }
}
