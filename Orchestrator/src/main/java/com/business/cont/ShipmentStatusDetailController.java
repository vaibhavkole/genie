package com.business.cont;

import com.business.mapper.ShipmentMapper;
import com.business.models.Shipment;
import com.business.models.ShipmentStatusDetail;
import com.business.repository.ShipmentStatusDetailRepository;
import com.business.service.ShipmentStatusDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@RestController
@RequestMapping("/shipmentStatusDetail")
public class ShipmentStatusDetailController {

    @Autowired
    ShipmentStatusDetailService shipmentStatusDetailService;

    /*@Autowired
    private ShipmentStatusDetailMapper shipmentStatusDetailMapper;*/

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getShipmentDetail(@RequestParam(value = "merchantName") String merchantName, @RequestParam(value = "shipmentRefNumber") String shipmentRefNumber) {
        List<ShipmentStatusDetail> shipment = shipmentStatusDetailService.getShipmentStatusDetails(merchantName, shipmentRefNumber);
        //return new ResponseEntity<>(shipmentStatusDetailMapper.convertToDto(shipment), HttpStatus.OK);
        return new ResponseEntity<>(shipment, HttpStatus.OK);

    }
}
