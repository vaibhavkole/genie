package com.genie.transport.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
@Entity
@Data
public class ShipmentReservationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "reservationDetails", referencedColumnName = "id")
    Reservation reservation;

    int shipmentId;

    double volume;

    Date creteDateTime;

    public ShipmentReservationDetail(Reservation reservation, int shipmentId, double volume) {
        this.reservation = reservation;
        this.shipmentId = shipmentId;
        this.volume = volume;
        this.creteDateTime = new Date();
    }
}
