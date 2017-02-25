package com.business.service;

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

    @Transactional(readOnly = true)
    public List<ShipmentStatusDetail> getShipmentStatusDetails() {
        return (List<ShipmentStatusDetail>) this.shipmentStatusDetailRepository.findAll();
    }

}
