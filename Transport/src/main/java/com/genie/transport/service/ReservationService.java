package com.genie.transport.service;

import com.genie.transport.model.Connection;
import com.genie.transport.model.Reservation;
import com.genie.transport.model.ShipmentReservationDetail;
import com.genie.transport.repository.IReservationRepository;
import com.genie.transport.repository.IShipmentReservationDetailRepository;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akashdeep.saluja on 26/02/17.
 */
@Service
public class ReservationService implements IReservationService {

    private final IReservationRepository reservationRepository;

    private final IShipmentReservationDetailRepository shipmentReservationDetailRepository;

    private final IConnectionService connectionService;

    @Autowired
    public ReservationService(IReservationRepository reservationRepository, IShipmentReservationDetailRepository shipmentReservationDetailRepository, IConnectionService connectionService) {
        this.reservationRepository = reservationRepository;
        this.shipmentReservationDetailRepository = shipmentReservationDetailRepository;
        this.connectionService = connectionService;
    }





    @Override
    public int createReservation(int shipmentId, double volume, Date date, int connectionId) {
        Connection connection = connectionService.getConnection(connectionId);
        Reservation reservation = reservationRepository.findByDateAndConnection(date, connection);
        if(reservation == null) {
            reservation = new Reservation(connection, date);
            reservationRepository.save(reservation);
        }
//        double capacity = connection.getCapacity();
        double bookedCapacity = reservation.getBookedCapacity();
        ShipmentReservationDetail reservationDetail = new ShipmentReservationDetail(reservation, shipmentId, volume);
        reservationDetail = shipmentReservationDetailRepository.save(reservationDetail);
        reservation.setBookedCapacity(bookedCapacity + volume);
        reservationRepository.save(reservation);
        return reservationDetail.getId();
    }

    @Override
    public Reservation getReservations(Date date, Connection connection) {
        return reservationRepository.findByDateAndConnection(date, connection);
    }
}
