package no.uib.ii.inf102.f18.mandatory2;

import java.util.LinkedList;

public class Vertex implements Comparable<Vertex> {
    private int id;
    public int distance;
    public LinkedList<Edge> nbrs;
    
    public Vertex(int id, int distance) {
        this.id = id;
        this.distance = distance;
        this.nbrs = new LinkedList<>();
    }
    
    public int id() {
        return this.id;
    }

    @Override
    public int compareTo(Vertex that) {
        return Integer.compare(this.distance, that.distance);
    }
    
}
