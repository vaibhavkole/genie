package com.genie.transport.service;

import com.genie.transport.dto.QuotationResponse;
import com.genie.transport.dto.ShipmentCreationRequest;
import com.genie.transport.model.Connection;
import com.genie.transport.model.Edge;
import com.genie.transport.model.Graph;
import com.genie.transport.model.Vertex;
import java.util.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akashdeep.saluja on 26/02/17.
 */
@Service
public class ShipmentService implements IShipmentService {


    private final IConnectionService connectionService;


    @Autowired
    public ShipmentService(IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @Override
    public List<Connection> getQuotationConnections(@NonNull ShipmentCreationRequest request) {
        Iterable<Connection> connections = connectionService.getAllConnections(new java.sql.Date(request.getStartDate()));
        List<Connection> finalConnections = new LinkedList<>();
        Graph graph = createGraph(connections, request.getVolume());
        DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph);
        algorithm.execute(new Vertex(new Integer(request.getSourceHub()).toString(), ""));
        List<Vertex> path = algorithm.getPath(new Vertex(new Integer(request.getDestinationHub()).toString(), ""));
        for(int i = 0; i < path.size() - 1; i++) {
            int source = Integer.parseInt(path.get(i).getId());
            int destination = Integer.parseInt(path.get(i+1).getId());
            Connection ansConnection = null;
            for(Connection connection: connections) {
                if(connection.getCapacity() > request.getVolume()) {
                    if (connection.getSourceHub() == source && connection.getDestinationHub() == destination) {
                        if (ansConnection == null) {
                            ansConnection = connection;
                        } else if (ansConnection.getCost() > connection.getCost()) {
                            ansConnection = connection;
                        }
                    }
                }
            }
            finalConnections.add(ansConnection);
        }
        return finalConnections;
    }

    @Override
    public QuotationResponse getQuotation(@NonNull ShipmentCreationRequest request) {
        List<Connection> connections = getQuotationConnections(request);

        double cost = 0;
        int days = 0;
        for(Connection connection: connections) {
            cost += connection.getCost()*request.getVolume();
            days += Math.ceil((double)connection.getTransitTime().getTime()/(24*60*60*1000));
        }
        QuotationResponse response = new QuotationResponse(cost, days);
        return response;
    }

    public  Graph createGraph(Iterable<Connection> connections, double volume) {
        Iterator iterator = connections.iterator();

        List<Vertex> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        Set<Integer> vertexIds = getUniqueVertices(connections);
        vertexIds.stream().forEach(i -> vertices.add(new Vertex(i.toString(), "")));
        Map<Integer, Edge> edgeMap = new HashMap<>();
        while (iterator.hasNext ()) {
            Connection element = (Connection) iterator.next ();
            if(element.getCapacity() < volume) {
                continue;
            }
            int sourceIndex = vertices.indexOf(new Vertex(new Integer(element.getSourceHub()).toString(), ""));
            int destinationIndex = vertices.indexOf(new Vertex(new Integer(element.getDestinationHub()).toString(), ""));
            Edge edge = new Edge(new Integer(element.getId()).toString(), vertices.get(sourceIndex), vertices.get(destinationIndex), (int)Math.round(element.getCost()));
            int hash = edge.getHash();
            if((edgeMap.containsKey(hash) && edgeMap.get(hash).getWeight() > edge.getWeight()) || !edgeMap.containsKey(hash)) {
                edgeMap.put(hash, edge);
            }
        }
        edges.addAll(edgeMap.values());
        return new Graph(vertices, edges);
    }

    private  Set<Integer> getUniqueVertices(Iterable<Connection> connections) {
        Iterator iterator = connections.iterator();
        Set<Integer> vertices = new HashSet<>();
        while (iterator.hasNext ()) {
            Connection element = (Connection) iterator.next ();
            vertices.add(element.getSourceHub());
            vertices.add(element.getDestinationHub());
        }
        return vertices;
    }
}
