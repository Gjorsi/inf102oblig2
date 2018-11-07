package no.uib.ii.inf102.f18.mandatory2;

import java.util.LinkedList;

public class Vertex {
    public int id;
    public LinkedList<Edge> nbrs;
    
    public Vertex(int id) {
        this.id = id;
        this.nbrs = new LinkedList<>();
    }
}
