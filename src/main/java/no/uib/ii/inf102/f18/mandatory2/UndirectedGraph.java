package no.uib.ii.inf102.f18.mandatory2;

import java.util.LinkedList;

public class UndirectedGraph implements IGraph<Integer> {

    LinkedList<Integer> adjList[];
    int nE = 0, nV = 0;
    
    @SuppressWarnings("unchecked")
    public UndirectedGraph(int n) {
        this.adjList = new LinkedList[n];
        this.nV = n;
        
        for (int i=0; i<n; i++) {
            this.adjList[i] = new LinkedList<>();
        }
    }

    @Override
    public void addEdge(int u, int v) {
        adjList[u].add(v);
        adjList[v].add(u);
        nE++;
    }

    @Override
    public int getSize() {
        return nV;
    }

    @Override
    public int nEdges() {
        return nE;
    }

    @Override
    public boolean areAdj(int u, int v) {
        for (int neighbour : this.adjList[u]) {
            if (neighbour == v) return true;
        }
        return false;
    }

    @Override
    public Iterable<Integer> adj(int u) {
        return this.adjList[u];
    }

}
