package com.business.service;

import com.business.dto.MerchantDto;
import com.business.models.Shipment;
import com.business.repository.ShipmentRepository;
import com.google.common.cache.CacheLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }
    public Shipment getShipmentDetails(String merchantName, String shipmentRefId){
        MerchantDto merchantInfo = requestHandler.getMerchantByName(merchantName);
        return shipmentRepository.findByMerchantIdAndShipmentRefId(merchantInfo.getMerchantId(), shipmentRefId);
    }

}
