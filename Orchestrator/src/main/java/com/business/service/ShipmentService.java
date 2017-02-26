package com.business.service;

import com.business.dto.CreatePickupRequest;
import com.business.dto.DeliverShipmentRequest;
import com.business.dto.MerchantDto;
import com.business.dto.PickupRequestResponse;
import com.business.models.CreateExpectationForShipment;
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

    @Autowired
    private AddressService addressService;

    public Shipment createShipment(Shipment shipment) {
        //Call Facilities for serviceability details
        //Call Facilities to book slot for PickupService
        //call Transportation to book slot and get expected delivery details
        //call Facilities to book slot for delivery
        shipment.setPickupAddress(addressService.addAddress(shipment.getPickupAddress()));
        shipment.setDeliveryAddress(addressService.addAddress(shipment.getDeliveryAddress()));
        Shipment createdShipment = shipmentRepository.save(shipment);
        //call Task service for expected pickup shipment
        //add shipment status details;
        return createdShipment;
    }
    public Shipment getShipmentDetails(String merchantName, String shipmentRefNumber){
        MerchantDto merchantInfo = requestHandler.getMerchantByName(merchantName);
        return shipmentRepository.findByMerchantIdAndShipmentRefNumber(merchantInfo.getMerchantId(), shipmentRefNumber);
    }



    public PickupRequestResponse createPickupRequest(CreatePickupRequest createPickupRequest, int locationId) {
        return httpRequestHandler.createHttpPickupRequest(createPickupRequest,locationId);
    }

    public DeliverShipmentRequest createDeliverRequest(DeliverShipmentRequest deliverShipmentRequest, int locationId) {
        return httpRequestHandler.createHttpDeliverRequest(deliverShipmentRequest, locationId);
    }

    public CreateExpectationForShipment createExpectedRequest(CreateExpectationForShipment createExpectationForShipment) {
        return httpRequestHandler.createExpectationForShipment(createExpectationForShipment);
    }

    public List<String> mark_runsheet_complete(Integer runsheetId) {
        return httpRequestHandler.mark_runsheet_complete(runsheetId);
    }

    public List<String> mark_pickup_sheet_complete(Integer pickupSheetId) {
        return httpRequestHandler.mark_pickup_sheet_complete(pickupSheetId);
    }

    public Integer markPickupComplete(int shipmentId, int hubId) {
        return 0;
    }


    public Integer markDelivered(int shipmentId, int hubId) {
        return 0;
    }


    public Integer markReceived(int shipmentId, int hubId) {
        return 0;
    }



    public int getNextHub(int hubId, int shipmentId) {
        return httpRequestHandler.getNextHub(hubId, shipmentId);
    }
}
