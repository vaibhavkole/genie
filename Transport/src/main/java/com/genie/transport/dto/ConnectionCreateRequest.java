package com.genie.transport.dto;

import lombok.Data;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
@Data
public class ConnectionCreateRequest {


    private int partnerId;

    private int sourceHub;

    private int destinationHub;

    private double cost;

    private double capacity;

    private int vendorConnectionId;

    private long transitTime;

}
