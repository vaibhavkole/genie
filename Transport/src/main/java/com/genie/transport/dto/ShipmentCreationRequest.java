package com.genie.transport.dto;

import lombok.Data;

/**
 * Created by akashdeep.saluja on 26/02/17.
 */
@Data
public class ShipmentCreationRequest {

    private int shipmentId;
    int sourceHub;
    int destinationHub;
    double volume;
    long startDate;

}
