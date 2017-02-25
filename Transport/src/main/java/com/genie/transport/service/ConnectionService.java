package com.genie.transport.service;

import com.genie.transport.model.Connection;
import com.genie.transport.model.Vendor;
import com.genie.transport.repository.IConnectionRepository;
import java.util.Date;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
@Service
public class ConnectionService implements IConnectionService {

    private final IConnectionRepository repository;

    @Autowired
    public ConnectionService(IConnectionRepository repository) {
        this.repository = repository;
    }




    @Override
    public int createConnection(int sourceHub, int destinationHub, double cost, double capacity, @NonNull Date transitTime, @NonNull Vendor vendor, int vendorConnectionId) {
        Connection connection  = new Connection(sourceHub, destinationHub, cost, capacity, vendor, vendorConnectionId, transitTime);
        return repository.save(connection).getId();
    }

    @Override
    public Connection getConnection(int id) {
        return repository.findOne(id);
    }
}
