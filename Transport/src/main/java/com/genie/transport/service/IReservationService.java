package com.genie.transport.service;

/**
 * Created by akashdeep.saluja on 26/02/17.
 */
public interface IReservationService {

    int createReservation(int shipmentId, double volume, java.sql.Date date, int connectionId);

}
