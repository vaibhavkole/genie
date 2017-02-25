package com.genie.transport.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.NoArgsConstructor;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
@Entity
@NoArgsConstructor
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

    @Transient
    private double availableCapacity = 0;

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

    public int getId() {
        return id;
    }

    public int getSourceHub() {
        return sourceHub;
    }

    public int getDestinationHub() {
        return destinationHub;
    }

    public double getCost() {
        return cost;
    }

    public double getCapacity() {
        return capacity;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Date getTransitTime() {
        return transitTime;
    }

    public int getVendorConnectionId() {
        return vendorConnectionId;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(double availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", sourceHub=" + sourceHub +
                ", destinationHub=" + destinationHub +
                ", cost=" + cost +
                ", capacity=" + capacity +

                ", transitTime=" + transitTime +
                ", vendorConnectionId=" + vendorConnectionId +

                ", updateDateTime=" + updateDateTime +
                '}';
    }
}
