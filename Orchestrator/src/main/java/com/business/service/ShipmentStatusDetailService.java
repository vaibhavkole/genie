package com.business.service;

import com.business.models.Shipment;
import com.business.models.ShipmentStatusDetail;
import com.business.models.Status;
import com.business.repository.ShipmentStatusDetailRepository;
import com.business.repository.StatusRepository;
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

    @Autowired
    StatusRepository statusRepository;

    @Transactional(readOnly = true)
    public List<ShipmentStatusDetail> getShipmentStatusDetails(String merchantName, String shipmentRefNumber) {
        Shipment shipment = shipmentService.getShipmentDetails(merchantName, shipmentRefNumber);
        return (List<ShipmentStatusDetail>) this.shipmentStatusDetailRepository.findByShipment(shipment);
    }


    public ShipmentStatusDetail addShipmentStatusDetail(ShipmentStatusDetail shipmentStatusDetail){
        return shipmentStatusDetailRepository.save(shipmentStatusDetail);
    }

    public ShipmentStatusDetail addShipmentStatus(Shipment shipment, StatusEnum status, Integer pickupHubId, Integer deliveryHuId, Integer currentHubId){
        ShipmentStatusDetail shipmentStatusDetail = new ShipmentStatusDetail();
        shipmentStatusDetail.setShipment(shipment);
        shipmentStatusDetail.setStatus(statusRepository.findByStatusName(status.name()));
        shipmentStatusDetail.setPickupHubId(pickupHubId);
        shipmentStatusDetail.setDeliveryHubId(deliveryHuId);
        shipmentStatusDetail.setCurrentHubId(currentHubId);
        return addShipmentStatusDetail(shipmentStatusDetail);
    }

}
