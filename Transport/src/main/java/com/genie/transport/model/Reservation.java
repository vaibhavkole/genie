package com.genie.transport.model;


import java.sql.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "reservations", referencedColumnName = "id")
    Connection connection;

    Date date;

    double bookedCapacity;

    @OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY)
    private List<ShipmentReservationDetail> reservationDetails;

    java.util.Date updateDateTime;

    public Reservation(Connection connection, Date date) {
        this.connection = connection;
        this.bookedCapacity = 0;
        this.date = date;
        this.updateDateTime = new java.util.Date();
    }




}
