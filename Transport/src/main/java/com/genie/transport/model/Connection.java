package com.genie.transport.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
@Entity
@Data
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int sourceHub;

    private int destinationHub;

    private double cost;

    private double capacity;

    @ManyToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Vendor vendor;

    private Date transitTime;

    private int vendorConnectionId;

    @OneToMany(mappedBy = "connection", fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    private Date updateDateTime;

    public Connection(int sourceHub, int destinationHub, double cost, double capacity,
                      Vendor vendor, int vendorConnectionId, Date transitTime) {
        this.sourceHub = sourceHub;
        this.destinationHub = destinationHub;
        this.cost = cost;
        this.capacity = capacity;
        this.vendor = vendor;
        this.vendorConnectionId = vendorConnectionId;
        this.transitTime = transitTime;
    }





}
