package com.business.cont;

import com.business.dto.CreatePickupRequest;
import com.business.dto.DeliverShipmentRequest;
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
    public ResponseEntity<?> createShipment(@RequestBody ShipmentDto shipmentDto) {
        Shipment shipment = shipmentMapper.convertToEntity(shipmentDto);
        Shipment createdShipment = shipmentService.createShipment(shipment);
        return new ResponseEntity<>(shipmentMapper.convertToDto(createdShipment), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getShipmentDetail(@RequestParam(value = "merchantName") String merchantName, @RequestParam(value = "shipmentRefNumber") String shipmentRefNumber) {
        Shipment shipment = shipmentService.getShipmentDetails(merchantName, shipmentRefNumber);
        return new ResponseEntity<>(shipmentMapper.convertToDto(shipment), HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.POST,value = "/create_pickup_request/{location_id}")
    public ResponseEntity<?> create_pickup_request() {
        CreatePickupRequest createPickupRequest = new CreatePickupRequest();
        createPickupRequest.setShipmentId(12);
        createPickupRequest.setPincode(110032);
        createPickupRequest.setAddress("adfasd");
        return new ResponseEntity<>(shipmentService.createPickupRequest(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/create_deliver_request/{location_id}")
    public ResponseEntity<?> create_deliver_request() {
        DeliverShipmentRequest deliverShipmentRequest = new DeliverShipmentRequest();
        deliverShipmentRequest.setShipmentId(19);
        deliverShipmentRequest.setPincode(110032);
        deliverShipmentRequest.setAddress("adfasd");
        return new ResponseEntity<>(shipmentService.createDeliverRequest(deliverShipmentRequest, 1), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST,value = "/mark_runsheet_complete/{location_id}")
    public ResponseEntity<?> mark_runsheet_complete() {
        ;
        return new ResponseEntity<>(shipmentService.mark_runsheet_complete(15), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/mark_pickup_sheet_complete/{location_id}")
    public ResponseEntity<?> mark_pickup_sheet_complete() {
        ;
        return new ResponseEntity<>(shipmentService.mark_pickup_sheet_complete(13), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/pickup_complete/{shipment_id}/{location_id}")
    public ResponseEntity<?> markPickupComplete(@PathVariable int shipment_id, @PathVariable int location_id) {
        ;
        return new ResponseEntity<>(shipmentService.markPickupComplete(shipment_id, location_id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/delivered/{shipment_id}/{location_id}")
    public ResponseEntity<?> markDelivered(@PathVariable int shipment_id, @PathVariable int location_id) {
        ;
        return new ResponseEntity<>(shipmentService.markDelivered(shipment_id, location_id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/received/{shipment_id}/{location_id}")
    public ResponseEntity<?> markReceived(@PathVariable int shipment_id, @PathVariable int location_id) {
        ;
        return new ResponseEntity<>(shipmentService.markReceived(shipment_id, location_id), HttpStatus.OK);
    }


}


