package com.vabsssss.dto;

/**
 * Created by dheeraj.mittal on 26/02/17.
 */
public class CreatePickupRequest {
    private Integer shipmentId;
    private String address;
    private Integer pincode;

    public Integer getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }
}
