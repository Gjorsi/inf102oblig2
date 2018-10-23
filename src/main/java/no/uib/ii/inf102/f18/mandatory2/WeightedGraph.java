package no.uib.ii.inf102.f18.mandatory2;

import java.util.HashMap;

public class WeightedGraph {

    private HashMap<Integer, Vertex> vertices;
    private int nE = 0, nV = 0;
    
    public WeightedGraph(int n) {
        this.vertices = new HashMap<>();
        this.nV = n;
        
        for (int i=0; i<n; i++) {
            this.vertices.put(i, new Vertex(i, Integer.MAX_VALUE));
        }
    }
    
    public void addEdge(int u, int v, int weight) {
        vertices.get(u).nbrs.add(new Edge(u,v,weight));
        vertices.get(v).nbrs.add(new Edge(v,u,weight));
        nE++;
    }

    public Vertex getVertex (int x) {
        return vertices.get(x);
    }
    
    public int getSize() {
        return nV;
    }

    public int nEdges() {
        return nE;
    }

    public boolean areAdj(int u, int v) {
        for (Edge e : vertices.get(u).nbrs) {
            if (e.dest == v) return true;
        }
        return false;
    }

    public Iterable<Vertex> vertices() {
        return vertices.values();
    }
    
    public Iterable<Edge> adj(int u) {
        return vertices.get(u).nbrs;
    }
    
    public void addFlight(int u, int v) {
        // might be slow if there are many edges per vertex
        for (Edge e : vertices.get(u).nbrs) {
            if (e.dest == v) e.flightRoute = true;
        }
    }
}
