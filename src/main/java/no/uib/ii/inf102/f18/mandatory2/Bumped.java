package no.uib.ii.inf102.f18.mandatory2;

import java.util.PriorityQueue;
import java.util.Queue;

public class Bumped {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        
        int n = io.getInt(), m = io.getInt(), f = io.getInt(), s = io.getInt(), t = io.getInt();
        
        WeightedGraph graph = new WeightedGraph(n);
        
        for (int i=0; i<m; i++) {
            graph.addEdge(io.getInt(), io.getInt(), io.getInt());
        }
        
        for (int i=0; i<f; i++) {
            graph.addFlight(io.getInt(), io.getInt());
        }
        
        dijkstras(graph, s, t);
        
        io.println(graph.getVertex(t).distance);
        
        io.close();
    }

    private static void dijkstras(WeightedGraph graph, int s, int t) {
        
        Queue<Vertex> working = new PriorityQueue<>();
        int parent[] = new int[graph.getSize()];
        graph.getVertex(s).distance = 0;
        
        working.add(graph.getVertex(s));
        
        A:while(!working.isEmpty()) {
            Vertex cur = working.poll();
            for(Edge e : cur.nbrs) {
                Vertex nbr = graph.getVertex(e.dest);
                if (parent[nbr.id()] == cur.id()) continue;
                if (nbr.distance > cur.distance + e.weight) {
                    nbr.distance = cur.distance+e.weight;
                    parent[nbr.id()] = cur.id();
                    working.offer(nbr);
                }
            }
        }
    }

}
