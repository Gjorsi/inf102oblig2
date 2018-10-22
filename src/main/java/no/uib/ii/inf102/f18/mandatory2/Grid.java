package no.uib.ii.inf102.f18.mandatory2;

import java.util.ArrayDeque;
import java.util.Queue;

public class Grid {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int n = io.getInt();
        int m = io.getInt();
        
        WeightedDirectedGraph graph = new WeightedDirectedGraph(n*m);
        
        String line = "";
        int weight = 0;
        int cur = -1;
        
        for (int i=0; i<n; i++) {
            line = io.getWord();
            for (int j=0; j<m; j++) {
                cur++;
                weight = Character.getNumericValue(line.charAt(j));
                if (weight == 0) continue; //current vertex is not connected to any other
                
                // can jump to a vertex going south in grid
                if (i+weight < n) {
                    graph.addWeightedEdge(cur, cur+(j+1)*weight, weight);
                }
                
                // can jump to a vertex going north in grid
                if (i-weight >= 0) {
                    graph.addWeightedEdge(cur, cur-(j+1)*weight, weight);
                }
                
                // can jump to a vertex going east in grid
                if (j+weight < m) {
                    graph.addWeightedEdge(cur, cur+weight, weight);
                }
                
                // can jump to a vertex going west in grid
                if (j-weight >= 0) {
                    graph.addWeightedEdge(cur, cur-weight, weight);
                }
            }
        }
        
        System.out.println("vertices: " + graph.getSize());
        System.out.println("n of edges: " + graph.nEdges());
        
        for (int i=0; i<graph.getSize(); i++) {
            for (Edge edge : graph.adj(i)) {
                System.out.println("Edge from " + edge.source + " to " + edge.dest + ", weight " + edge.weight);
            }
        }
        
        int distance = breadthFirstSearch(graph, n*m);
        
        if (distance < Integer.MAX_VALUE) io.println(distance);
        else io.println(-1);
        
        io.close();
    }

    private static int breadthFirstSearch(WeightedDirectedGraph graph, int target) {
        int distance[] = new int[target+1];
        for (int i=2; i<target+1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        
        Queue<Integer> queue  = new ArrayDeque<>();
        queue.add(1);
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (Edge edge : graph.adj(vertex)) {
                if (distance[edge.dest] > (distance[edge.source] + edge.weight)) {
                    distance[edge.dest] = distance[edge.source] + edge.weight;
                }
                queue.add(edge.dest);
            }
        }
        
        
        return distance[target];
    }

}
