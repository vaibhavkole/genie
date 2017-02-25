package com.genie.transport.controller;

import com.genie.transport.dto.QuotationResponse;
import com.genie.transport.dto.ShipmentCreationRequest;
import com.genie.transport.service.IShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by akashdeep.saluja on 26/02/17.
 */
@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    IShipmentService service;

    @RequestMapping(method = RequestMethod.POST, value = "/quotation")
    @ResponseBody
    public ResponseEntity createPartner(@RequestBody ShipmentCreationRequest request) {
        QuotationResponse response = service.getQuotation(request);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
