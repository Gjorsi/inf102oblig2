package no.uib.ii.inf102.f18.mandatory2;

import java.util.LinkedList;

public class Vertex implements Comparable<Vertex> {
    public int id;
    public long distance;
    public long flightDistance;
    public LinkedList<Edge> nbrs;
    
    public Vertex(int id, long distance, long flightDistance) {
        this.id = id;
        this.distance = distance;
        this.flightDistance = flightDistance;
        this.nbrs = new LinkedList<>();
    }

    @Override
    public int compareTo(Vertex that) {
        return Long.compare(Math.min(this.distance,  this.flightDistance), Math.min(that.distance, that.flightDistance));
    }
}
