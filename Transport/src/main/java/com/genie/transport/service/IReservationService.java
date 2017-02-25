package com.genie.transport.service;

import com.genie.transport.model.Connection;
import com.genie.transport.model.Reservation;
import java.sql.Date;

/**
 * Created by akashdeep.saluja on 26/02/17.
 */
public interface IReservationService {

    int createReservation(int shipmentId, double volume, java.sql.Date date, int connectionId);

    Reservation getReservations(Date date, Connection connection);

    int getNextHub(int shipmentId, int hub);

}
