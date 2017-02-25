package com.genie.transport.service;

import com.genie.transport.model.Connection;
import com.genie.transport.model.Vendor;
import java.util.Date;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
public interface IConnectionService {

    int createConnection(int sourceHub, int destinationHub, double cost, double capacity,
                          Date transitTime, Vendor vendor, int vendorConnectionId);

    Connection getConnection(int id);

    Iterable<Connection> getAllConnections(java.sql.Date date);

}
