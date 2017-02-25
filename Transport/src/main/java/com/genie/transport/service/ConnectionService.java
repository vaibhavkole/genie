package com.genie.transport.service;

import com.genie.transport.model.Connection;
import com.genie.transport.model.Reservation;
import com.genie.transport.model.Vendor;
import com.genie.transport.repository.IConnectionRepository;
import com.genie.transport.repository.IReservationRepository;
import java.util.Date;
import java.util.Iterator;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
@Service
public class ConnectionService implements IConnectionService {

    private final IConnectionRepository repository;
    private final IReservationRepository reservationRepository;

    @Autowired
    public ConnectionService(IConnectionRepository repository, IReservationRepository reservationRepository) {
        this.repository = repository;
        this.reservationRepository = reservationRepository;
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

    @Override
    public Iterable<Connection> getAllConnections(java.sql.Date date) {
        Iterable<Connection> connections = repository.findAll();
        Iterator iterator = connections.iterator();
        while (iterator.hasNext ()) {
            Connection element = (Connection) iterator.next ();
            Reservation reservation = reservationRepository.findByDateAndConnection(date, element);
            if(reservation == null) {
                reservation = new Reservation(element, date);
                reservationRepository.save(reservation);
            }
            element.setCapacity(element.getCapacity() - reservation.getBookedCapacity());
        }
        return connections;

    }


}
