package no.uib.ii.inf102.f18.mandatory2;

public class WheresMyInternet {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        
        int n = io.getInt();
        int m = io.getInt();
        
        IGraph<Integer> graph = new UndirectedGraph(n+1);
        
        for (int i=0; i<m; i++) {
            graph.addEdge(io.getInt(), io.getInt());
        }
        
        boolean hasInternet[] = depthFirstSearch(graph);
        boolean allConnected = true;
        
        //print the houses not connected to the Internet.
        for (int i=2; i<=n; i++) {
            if (!hasInternet[i]) {
                io.println(i);
                allConnected = false;
            }
        }
        
        if (allConnected) io.println("Connected");
        
        io.close();
    }

    /**
     * A DFS which starts at house 1 and marks every house it visits in the hasInt[] array
     * @param graph the graph in which to search
     * @return a boolean array indicating which houses are connected
     */
    private static boolean[] depthFirstSearch(IGraph<Integer> graph) {
        boolean hasInt[] = new boolean [graph.getSize()+1];
        
        dfs(graph, hasInt, 1);
        
        return hasInt;
    }
    
    private static void dfs (IGraph<Integer> graph, boolean[] hasInt, int u) {
        hasInt[u] = true;
        for(int nbr : graph.adj(u)) {
            if (!hasInt[nbr]) {
                dfs(graph, hasInt, nbr);
            }
        }
    }
}
