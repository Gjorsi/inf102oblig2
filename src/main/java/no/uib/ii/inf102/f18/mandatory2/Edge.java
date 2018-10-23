package no.uib.ii.inf102.f18.mandatory2;

public class Edge {

    int dest;
    int weight;
    boolean flightRoute;
    
    public Edge (int destination, int weight, boolean flight) {
        this.dest = destination;
        this.weight = weight;
        this.flightRoute = flight;
    }

}
