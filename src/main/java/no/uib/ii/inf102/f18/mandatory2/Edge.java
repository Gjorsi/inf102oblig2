package no.uib.ii.inf102.f18.mandatory2;

public class Edge {

    int source;
    int dest;
    int weight;
    boolean flightRoute;
    
    public Edge (int source, int destination, int weight) {
        this.source = source;
        this.dest = destination;
        this.weight = weight;
        this.flightRoute = false;
    }

}
