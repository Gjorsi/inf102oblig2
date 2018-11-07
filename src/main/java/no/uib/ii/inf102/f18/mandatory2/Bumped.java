package no.uib.ii.inf102.f18.mandatory2;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Carl August Gjørsvik
 *
 */
public class Bumped {
    
    static Long[] dist;
    static Long[] fdist;

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
        
        dijkstras(graph, s, n, t);
        
        io.println(Math.min(dist[t], fdist[t]));
        
        io.close();
    }

    /**
     * A modified implementation of Dijkstra's shortest path. <br>
     * It takes into account that some edges are "flightRoutes", with cost zero,
     * but only one flight might be used in the path.
     * 
     * To make sure the shortest path is chosen with maximum one flight,
     * this implementation stores two separate distance-variables for each vertex; <br>
     * <b>distance</b> - represents the distance travelled to reach the vertex using roads only <br>
     * <b>flightDistance</b> - represents the distance travelled to reach the vertex using exactly one flight
     * 
     * @param graph the graph in which to find the path
     * @param s starting vertex
     * @param t target vertex
     */
    private static void dijkstras(WeightedGraph graph, int s, int n, int t) {
        
        IndexMinPQ<Long> working = new IndexMinPQ<>(n);
        graph.getVertex(s).distance = 0;
        boolean visited[] = new boolean[n];
        HashSet<Integer> inQueue = new HashSet<>();
        dist = new Long[n];
        fdist = new Long[n]; 
        
        for (int i=0; i<n ; i++) {
            dist[i] = fdist[i] = Long.MAX_VALUE/2;
        }
        
        dist[s] = fdist[s] = 0L;
        
        working.add(s, 0L);
        inQueue.add(s);
        
        while(!working.isEmpty()) {
            int cur = working.poll();
            inQueue.remove(cur);
            if (cur == t) return;
            visited[cur] = true;
            
            for(Edge e : graph.getVertex(cur).nbrs) {
                if (visited[e.dest]) continue;
                
                int nbr = e.dest;
                
                boolean update = false;
                
                //edge is a flightRoute, update flightDistance if it provides a shorter route
                if (e.flightRoute) {
                    if (dist[cur] < fdist[nbr]) {
                        fdist[nbr] = dist[cur];
                        update = true;
                    }
                    
                //edge is a road, update nbr's road distance and flightDistance if necessary
                } else {
                    
                    //update distance using roads only (compare distance to Long.MAX_VALUE first to avoid overflow issues)
                    if (dist[cur] + e.weight < dist[nbr]) {
                        dist[nbr] = dist[cur]+e.weight;
                        update = true;
                    }
                    
                    //update flightDistance to nbr if there is a path to cur using a flight
                    if (fdist[cur] + e.weight < fdist[nbr]) {
                        fdist[nbr] = fdist[cur] + e.weight;
                        update = true;
                    }
                }
                
                if (update) {
                    if (inQueue.contains(nbr)) {
                        working.changeKey(nbr, Math.min(dist[nbr], fdist[nbr]));
                    } else {
                        working.add(nbr, Math.min(dist[nbr], fdist[nbr]));
                    }
                    inQueue.add(nbr);
                }
            }
        }
    }
}
