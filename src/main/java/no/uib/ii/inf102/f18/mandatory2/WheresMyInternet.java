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
        
        for (int i=2; i<=n; i++) {
            if (!hasInternet[i]) {
                io.println(i);
                allConnected = false;
            }
        }
        
        if (allConnected) io.println("Connected");
        
        io.close();
    }

    private static boolean[] depthFirstSearch(IGraph<Integer> graph) {
        boolean hasInt[] = new boolean [graph.getSize()+1];
        int parent[] = new int[graph.getSize()+1];
        for (int i=1; i<=graph.getSize(); i++) {
            parent[i] = -1;
        }
        
        dfs(graph, hasInt, parent, 1);
        
        return hasInt;
    }
    
    private static void dfs (IGraph<Integer> graph, boolean[] hasInt, int[] parent, int u) {
        hasInt[u] = true;
        for(int nbr : graph.adj(u)) {
            if (!hasInt[nbr]) {
                parent[nbr] = u;
                dfs(graph, hasInt, parent, nbr);
            }
        }
    }
}
