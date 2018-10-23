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

    /**
     * A modified implementation of Dijkstra's shortest path. <br>
     * It takes into account that some edges are "flightRoutes", with cost zero,
     * but only one flight might be used in the path.
     * 
     * To make sure the shortest path is chosen with maximum one flight,
     * this implementation stores two separate distance-variables for each vertex; <br>
     * <b>distance</b> - represents the distance traveLled to reach the vertex using roads only <br>
     * <b>flightDistance</b> - represents the distance travelled to reach the vertex using exactly one flight
     * 
     * @param graph the graph in which to find the path
     * @param s starting vertex
     * @param t target vertex
     */
    private static void dijkstras(WeightedGraph graph, int s, int t) {
        
        Queue<Vertex> working = new PriorityQueue<>();
        graph.getVertex(s).distance = 0;
        
        working.add(graph.getVertex(s));
        
        while(!working.isEmpty()) {
            Vertex cur = working.poll();
            
            for(Edge e : cur.nbrs) {
                Vertex nbr = graph.getVertex(e.dest);
                
                boolean update = false;
                
                //edge is a flightRoute, update flightDistance if it provides a shorter route
                if (e.flightRoute) {
                    if (cur.distance < nbr.flightDistance) {
                        nbr.flightDistance = cur.distance;
                        update = true;
                    }
                    
                //edge is a road, update nbr's road distance and flightDistance if necessary
                } else {
                    
                    //update distance using roads only (compare distance to Long.MAX_VALUE first to avoid overflow issues)
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
                    working.offer(nbr);
                }
            }
        }
    }
}
