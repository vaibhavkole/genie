package com.business.cont;

import com.business.dto.ShipmentDto;
import com.business.mapper.ShipmentMapper;
import com.business.models.Shipment;
import com.business.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private ShipmentMapper shipmentMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addMerchant(@RequestBody ShipmentDto shipmentDto) {
        Shipment shipment = shipmentMapper.convertToEntity(shipmentDto);
        Shipment createdShipment = shipmentService.createShipment(shipment);
        return new ResponseEntity<>(shipmentMapper.convertToDto(createdShipment), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getShipmentDetail(@RequestParam(value = "merchantName") String merchantName, @RequestParam(value = "shipmentRefId") String shipmentRefId) {
        Shipment shipment = shipmentService.getShipmentDetails(merchantName, shipmentRefId);
        return new ResponseEntity<>(shipmentMapper.convertToDto(shipment), HttpStatus.OK);
    }

}
