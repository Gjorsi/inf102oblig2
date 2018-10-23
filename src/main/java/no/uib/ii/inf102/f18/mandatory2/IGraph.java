package no.uib.ii.inf102.f18.mandatory2;

/**
 * @author Carl August Gjørsvik
 * @param <E>
 */
public interface IGraph<E> {
    
    /**
     * Add edge between vertex u and vertex v
     * 
     * @param u vertex
     * @param v vertex
     */
    void addEdge(int u, int v);
    
    /**
     * @return number of vertices in graph
     */
    int getSize();
    
    /**
     * @return number of edges in graph
     */
    int nEdges();
    
    /**
     * Check whether vertices u and v are connected by an edge. <br>
     * Note that when used in a directed graph, it should return false if v has an edge to u, 
     * but u has no edge to v.
     * 
     * @param u vertex
     * @param v vertex
     * @return true if connected by an edge
     */
    boolean areAdj(int u, int v);
    
    /**
     * Get an iterator over the neighbours of vertex u
     * 
     * @param u vertex
     * @return iterator
     */
    Iterable<E> adj(int u);

}
