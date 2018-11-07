package no.uib.ii.inf102.f18.mandatory2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;

public class BuildDeps {

    private static int time;
    private static DirectedGraph graph;
    private static int[] pre, post;
    private static ArrayDeque<Integer> stack;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Kattio io = new Kattio(System.in, System.out);
        
        int n = Integer.parseInt(br.readLine());
        graph = new DirectedGraph(n);
        HashMap<String, Integer> nodes = new HashMap<>();
        String names[] = new String[n];
        int nodeNumber = -1;
        
        // read the impractical input and turn string identifiers into integers and create the graph
        for (int i=0; i<n; i++) {
            String dependencies[] = br.readLine().split("\\s");
            String id = dependencies[0].substring(0, dependencies[0].length()-1); //remove : from substring after split
            if (!nodes.containsKey(id)) {
                nodes.put(id, ++nodeNumber);
                names[nodeNumber] = id;
            }
            
            for (int j=1; j<dependencies.length; j++) {
                if (!nodes.containsKey(dependencies[j])) { 
                    nodes.put(dependencies[j], ++nodeNumber);
                    names[nodeNumber] = dependencies[j];
                }
                graph.addEdge(nodes.get(dependencies[j]), nodes.get(id));
            }
        }
        
        String target = br.readLine();
        
        topologicalSort(nodes.get(target));
        n = stack.size();
        
        for (int i=0; i<n; i++) {
            io.println(names[stack.poll()]);
        }
        
        br.close();
        io.close();
    }

    /**
     * Topological sort - sorts everything connected from vertex t. 
     * In a DAG, it will sort a subtree branching from vertex t.
     * @param t start the DFS from this vertex
     */
    private static void topologicalSort(int t) {
        pre = new int[graph.getSize()];
        post = new int[graph.getSize()];
        stack = new ArrayDeque<Integer>();
        
        for (int i=0; i<graph.getSize(); i++) {
            pre[i] = -1;
            post[i] = -1;
        }
        
        time = 0;
        depthFirstSearch(t);
    }

    private static void depthFirstSearch(int u) {
        pre[u] = time++;
        
        for(int nbr : graph.adj(u)) {
            if (pre[nbr] < 0) {
                depthFirstSearch(nbr);
            } 
        }
        
        post[u] = time++;
        stack.push(u);
    }

}
