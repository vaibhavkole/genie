package com.business.service;

import com.business.models.Shipment;
import com.business.models.ShipmentStatusDetail;
import com.business.repository.ShipmentStatusDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Service
public class ShipmentStatusDetailService {
    @Autowired
    ShipmentStatusDetailRepository shipmentStatusDetailRepository;

    @Autowired
    ShipmentService shipmentService;

    @Transactional(readOnly = true)
    public List<ShipmentStatusDetail> getShipmentStatusDetails(String merchantName, String shipmentRefNumber) {
        Shipment shipment = shipmentService.getShipmentDetails(merchantName, shipmentRefNumber);
        return (List<ShipmentStatusDetail>) this.shipmentStatusDetailRepository.findByShipment(shipment);
    }

}
