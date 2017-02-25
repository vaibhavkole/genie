package com.genie.transport.service;

import com.genie.transport.dto.QuotationResponse;
import com.genie.transport.dto.ShipmentCreationRequest;
import com.genie.transport.model.Connection;
import java.util.List;

/**
 * Created by akashdeep.saluja on 26/02/17.
 */
public interface IShipmentService {
    List<Connection> getQuotationConnections(ShipmentCreationRequest request);
    QuotationResponse getQuotation(ShipmentCreationRequest request);
    QuotationResponse makeBooking(ShipmentCreationRequest request);
    int getNextHub(int shipmentId, int hub);

}
