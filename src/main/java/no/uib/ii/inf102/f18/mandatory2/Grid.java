package no.uib.ii.inf102.f18.mandatory2;

import java.util.ArrayDeque;
import java.util.Queue;

public class Grid {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int n = io.getInt();
        int m = io.getInt();
        
        IGraph<Integer> graph = new DirectedGraph(n*m);
        
        String line = "";
        int weight = 0;
        int cur = 0;
        
        for (int i=1; i<=n; i++) {
            line = io.getWord();
            for (int j=1; j<=m; j++) {
                cur++;
                weight = Character.getNumericValue(line.charAt(j-1));
                if (weight == 0) continue; //current vertex is not connected to any other
                
                // can jump to a vertex going east in grid
                if (j+weight <= m) {
                    graph.addEdge(cur-1, cur+weight-1);
                }
                
                // can jump to a vertex going south in grid
                if (i+weight <= n) {
                    graph.addEdge(cur-1, cur+m*weight-1);
                }
                
                // can jump to a vertex going west in grid
                if (j-weight > 0) {
                    graph.addEdge(cur-1, cur-weight-1);
                }
                
                // can jump to a vertex going north in grid
                if (i-weight > 0) {
                    graph.addEdge(cur-1, cur-m*weight-1);
                }
            }
        }
        
        int distance = breadthFirstSearch(graph, n*m-1);
        
        if (distance < Integer.MAX_VALUE) io.println(distance);
        else io.println(-1);
        
        io.close();
    }

    private static int breadthFirstSearch(IGraph<Integer> graph, int target) {
        int distance[] = new int[target+1];
        int parent[] = new int[target+1];
        for (int i=1; i<=target; i++) {
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        
        Queue<Integer> queue  = new ArrayDeque<>();
        queue.add(0);
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int nbr : graph.adj(vertex)) {
                if (distance[nbr] > (distance[vertex] + 1)) {
                    parent[nbr] = vertex;
                    distance[nbr] = distance[vertex] + 1;
                    queue.add(nbr);
                }
            }
        }
        
        
        return distance[target];
    }

}
