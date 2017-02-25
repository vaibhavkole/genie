package com.business.service;

import com.business.dto.CreatePickupRequest;
import com.business.dto.DeliverShipmentRequest;
import com.business.dto.MerchantDto;
import com.business.dto.PickupRequestResponse;
import com.business.models.Shipment;
import com.business.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Service
public class ShipmentService {

    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    private HttpRequestHandler requestHandler;

    @Autowired
    HttpRequestHandler httpRequestHandler;

    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }
    public Shipment getShipmentDetails(String merchantName, String shipmentRefId){
        MerchantDto merchantInfo = requestHandler.getMerchantByName(merchantName);
        return shipmentRepository.findByMerchantIdAndShipmentRefId(merchantInfo.getMerchantId(), shipmentRefId);
    }



    public PickupRequestResponse createPickupRequest(CreatePickupRequest createPickupRequest, int locationId) {
        return httpRequestHandler.createHttpPickupRequest(createPickupRequest,locationId);
    }

    public DeliverShipmentRequest createDeliverRequest(DeliverShipmentRequest deliverShipmentRequest, int locationId) {
        return httpRequestHandler.createHttpDeliverRequest(deliverShipmentRequest, locationId);
    }

    public List<String> mark_runsheet_complete(Integer runsheetId) {
        return httpRequestHandler.mark_runsheet_complete(runsheetId);
    }

    public List<String> mark_pickup_sheet_complete(Integer pickupSheetId) {
        return httpRequestHandler.mark_pickup_sheet_complete(pickupSheetId);
    }

}
