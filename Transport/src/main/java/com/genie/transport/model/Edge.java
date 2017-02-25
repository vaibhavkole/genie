package com.genie.transport.model;

/**
 * Created by akashdeep.saluja on 26/02/17.
 */


public class Edge  {
    private final String id;
    private final Vertex source;
    private final Vertex destination;
    private final int weight;

    public Edge(String id, Vertex source, Vertex destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }
    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;

        return id.equals(edge.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public int getHash() {
        return 10000*Integer.parseInt(source.getId()) + 10000*Integer.parseInt(destination.getId());
    }
}
