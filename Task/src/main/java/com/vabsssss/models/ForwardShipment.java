package com.vabsssss.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by dheeraj.mittal on 25/02/17.
 */

@Entity(name = "forward_shipment")
public class ForwardShipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer destinationLocationId;
    private  Integer sourceLocationId;
    private Integer shipmentId;

    public Integer getDestinationLocationId() {
        return destinationLocationId;
    }

    public void setDestinationLocationId(Integer destinationLocationId) {
        this.destinationLocationId = destinationLocationId;
    }

    public Integer getSourceLocationId() {
        return sourceLocationId;
    }

    public void setSourceLocationId(Integer sourceLocationId) {
        this.sourceLocationId = sourceLocationId;
    }

    public Integer getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
