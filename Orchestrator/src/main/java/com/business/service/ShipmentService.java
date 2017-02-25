package com.business.service;

import com.business.dto.MerchantDto;
import com.business.models.Shipment;
import com.business.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
