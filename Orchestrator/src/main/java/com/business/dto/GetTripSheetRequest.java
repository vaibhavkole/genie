package com.business.dto;

import java.util.List;

/**
 * Created by dheeraj.mittal on 26/02/17.
 */
public class GetTripSheetRequest {

    private Integer tripSheetId;

    public static class ShipmentDetails {
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

        public ShipmentDetails(Integer shipmentId, String address, Integer pincode) {
            this.shipmentId = shipmentId;
            this.address = address;
            this.pincode = pincode;
        }
    }


    private List<ShipmentDetails> shipmentDetails;

    public Integer getTripSheetId() {
        return tripSheetId;
    }

    public void setTripSheetId(Integer tripSheetId) {
        this.tripSheetId = tripSheetId;
    }

    public List<ShipmentDetails> getShipmentDetails() {
        return shipmentDetails;
    }

    public void setShipmentDetails(List<ShipmentDetails> shipmentDetails) {
        this.shipmentDetails = shipmentDetails;
    }
}
