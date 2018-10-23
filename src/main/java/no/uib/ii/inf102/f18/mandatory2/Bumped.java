package no.uib.ii.inf102.f18.mandatory2;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Carl August Gjørsvik
 *
 */
public class Bumped {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        
        int n = io.getInt(), m = io.getInt(), f = io.getInt(), s = io.getInt(), t = io.getInt();
        
        WeightedGraph graph = new WeightedGraph(n);
        
        // read and add road edges
        for (int i=0; i<m; i++) {
            graph.addEdge(io.getInt(), io.getInt(), io.getInt());
        }
        
        // read and add flightRoute edges
        for (int i=0; i<f; i++) {
            graph.addFlight(io.getInt(), io.getInt());
        }
        
        dijkstras(graph, s, t);
        
        io.println(Math.min(graph.getVertex(t).distance, graph.getVertex(t).flightDistance));
        
        io.close();
    }

    private static void dijkstras(WeightedGraph graph, int s, int t) {
        
        Queue<Vertex> working = new PriorityQueue<>();
        int parent[] = new int[graph.getSize()];
        for (int i=0; i<parent.length; i++) {
            parent[i] = -1;
        }
        graph.getVertex(s).distance = 0;
        
        working.add(graph.getVertex(s));
        
        while(!working.isEmpty()) {
            Vertex cur = working.poll();
            
            for(Edge e : cur.nbrs) {
                Vertex nbr = graph.getVertex(e.dest);
                if (parent[nbr.id()] == cur.id()) continue; //unnecessary?
                
                boolean update = false;
                
                //edge is a flightRoute, update flightDistance if it provides a shorter route
                if (e.flightRoute) {
                    if (cur.distance < nbr.flightDistance) {
                        nbr.flightDistance = cur.distance;
                        update = true;
                    }
                    
                //edge is a road, update nbr's road distance and flightDistance if necessary
                } else {
                    
                    //update distance using roads only
                    if (cur.distance < Long.MAX_VALUE && (cur.distance + e.weight < nbr.distance)) {
                        nbr.distance = cur.distance+e.weight;
                        update = true;
                    }
                    
                    //update flightDistance to nbr if there is a path to cur using a flight
                    if (cur.flightDistance < Long.MAX_VALUE && (cur.flightDistance + e.weight < nbr.flightDistance)) {
                        nbr.flightDistance = cur.flightDistance + e.weight;
                        update = true;
                    }
                }
                
                if (update) {
                    parent[nbr.id()] = cur.id();
                    working.offer(nbr);
                }
            }
        }
    }
}
