package com.genie.transport.test.service;

import com.genie.transport.model.Connection;
import com.genie.transport.model.Vendor;
import com.genie.transport.repository.IConnectionRepository;
import com.genie.transport.service.ConnectionService;
import com.genie.transport.service.IConnectionService;
import com.genie.transport.test.AbstractTest;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
public class ConnectionServiceTest {


    public static class CreateConnection extends AbstractTest {

        @Mock
        IConnectionRepository repository;

        IConnectionService service;

        @Before
        public void setUp() {
            service = new ConnectionService(repository);
        }


        @Test
        public void normalCase() {
            int sourceHub = 12;
            int destinationHub = 23;
            double cost = 1.1;
            double capacity = 2.2;
            int vendorId = 13;
            Vendor vendor = new Vendor("12", "23");
            Date transitTime = new Date(1234l);
            int vendorConnectionId = 12;
            Connection connection = new Connection(sourceHub, destinationHub, cost, capacity, vendor,
                    vendorConnectionId, transitTime);

            when(repository.save(any(Connection.class))).thenReturn(connection);
            service.createConnection(sourceHub, destinationHub, cost, capacity, transitTime, vendor, vendorConnectionId);

        }


    }
}
