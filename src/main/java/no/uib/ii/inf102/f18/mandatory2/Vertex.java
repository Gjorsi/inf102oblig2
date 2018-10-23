package no.uib.ii.inf102.f18.mandatory2;

import java.util.LinkedList;

public class Vertex implements Comparable<Vertex> {
    private int id;
    public int distance;
    public int flightDistance;
    public LinkedList<Edge> nbrs;
    
    public Vertex(int id, int distance, int flightDistance) {
        this.id = id;
        this.distance = distance;
        this.flightDistance = flightDistance;
        this.nbrs = new LinkedList<>();
    }
    
    public int id() {
        return this.id;
    }

    @Override
    public int compareTo(Vertex that) {
        return Integer.compare(Math.min(this.distance,  this.flightDistance), Math.min(that.distance, that.flightDistance));
    }
    
}
