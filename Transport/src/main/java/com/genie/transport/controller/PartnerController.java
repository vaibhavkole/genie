package com.genie.transport.controller;

import com.genie.transport.dto.ConnectionCreateRequest;
import com.genie.transport.dto.PartnerCreationRequest;
import com.genie.transport.service.IPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
@RestController
@RequestMapping("/partner")
public class PartnerController {

    @Autowired
    IPartnerService service;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    @ResponseBody
    public ResponseEntity createPartner(@RequestBody PartnerCreationRequest request) {
        int id = service.createPartner(request);
        return  new ResponseEntity<>(id,HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add-connection")
    @ResponseBody
    public ResponseEntity createConnection(@RequestBody ConnectionCreateRequest request) {
        service.createConnection(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }



}
