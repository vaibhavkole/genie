package com.vabsssss.cont;

import com.vabsssss.dto.CreatePickupRequest;
import com.vabsssss.dto.CreateSheetModel;
import com.vabsssss.models.DeliverShipmentRequest;
import com.vabsssss.dto.GetTripSheetRequest;
import com.vabsssss.models.*;
import com.vabsssss.repository.*;
import com.vabsssss.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dheeraj.mittal on 25/02/17.
 */


@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ForwardShipmentRepository forwardShipmentRepository;

    @Autowired
    ReceiveShipmentRepository receiveShipmentRepository;

    @Autowired
    TaskIdShipmentMappingRepository taskIdShipmentMappingRepository;

    @Autowired
    PickupRequestRepository pickupRequestRepository;

    @Autowired
    DeliverShipmentRequestRepository deliverShipmentRequestRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createTaskSheet(@RequestBody CreateSheetModel createSheetModel) {
        //return new ResponseEntity<>(tempRepository.save(input), HttpStatus.CREATED);
        return new ResponseEntity<>(taskService.createTripSheet(createSheetModel), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/forward_shipment",method = RequestMethod.POST)
    public ResponseEntity<?> forwardShipment(@RequestBody ForwardShipment forwardShipment) {
        //return new ResponseEntity<>(tempRepository.save(input), HttpStatus.CREATED);
        return new ResponseEntity<>(forwardShipmentRepository.save(forwardShipment), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/expect_shipment",method = RequestMethod.POST)
    public ResponseEntity<?> expectShipment(@RequestBody ReceiveShipment receiveShipment) {
        //return new ResponseEntity<>(tempRepository.save(input), HttpStatus.CREATED);
        receiveShipment.setDate(new Date());
        return new ResponseEntity<>(receiveShipmentRepository.save(receiveShipment), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/mark_runsheet_complete/{sheet}",method = RequestMethod.POST)
    public ResponseEntity<?> markRunSheetComplete(@PathVariable Integer sheet) {
        Task task = taskRepository.findOne(sheet);
        task.setActive(false);
        task.setDate(new Date());
        taskRepository.save(task);
        List<TaskIdShipmentMapping> taskIdShipmentMappings = taskIdShipmentMappingRepository.findByTaskId(sheet);
        List<Integer> shipmentIdDelivered = new ArrayList<>();
        for(TaskIdShipmentMapping taskIdShipmentMapping : taskIdShipmentMappings) {
            shipmentIdDelivered.add(taskIdShipmentMapping.getShipmentId());
        }
        return new ResponseEntity<>(shipmentIdDelivered,HttpStatus.FOUND);
    }

    @RequestMapping(value = "/mark_pickup_sheet_complete/{sheet}",method = RequestMethod.POST)
    public ResponseEntity<?> markPickupSheetComplete(@PathVariable Integer sheet) {
        Task task = taskRepository.findOne(sheet);
        task.setActive(false);
        task.setDate(new Date());
        taskRepository.save(task);
        List<TaskIdShipmentMapping> taskIdShipmentMappings = taskIdShipmentMappingRepository.findByTaskId(sheet);
        List<Integer> shipmentIdDelivered = new ArrayList<>();
        for(TaskIdShipmentMapping taskIdShipmentMapping : taskIdShipmentMappings) {
            shipmentIdDelivered.add(taskIdShipmentMapping.getShipmentId());
        }
        return new ResponseEntity<>(shipmentIdDelivered,HttpStatus.FOUND);
    }

    @RequestMapping(value = "/create_pickup_request/{location_id}", method = RequestMethod.POST)
    public ResponseEntity<?> createPickupRequest(@PathVariable Integer location_id,
                                                 @RequestBody CreatePickupRequest createPickupRequest ) {

        PickupRequestResponse pickupRequest = new PickupRequestResponse();
        pickupRequest.setLocationId(location_id);
        pickupRequest.setAddress(createPickupRequest.getAddress());
        pickupRequest.setPincode(createPickupRequest.getPincode());
        pickupRequest.setShipmentId(createPickupRequest.getShipmentId());
        return new ResponseEntity<>(pickupRequestRepository.save(pickupRequest), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "/ready_for_deliver/{location_id}", method = RequestMethod.POST)
    public ResponseEntity<?> createPickupRequest(@PathVariable Integer location_id,
                                                 @RequestBody DeliverShipmentRequest deliverShipmentRequest ) {
        return new ResponseEntity<>(deliverShipmentRequestRepository.save(deliverShipmentRequest),HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTaskDetails(@PathVariable Integer id) {

        Task task = taskRepository.findOne(id);
        List<TaskIdShipmentMapping> taskIdShipmentMappings = taskIdShipmentMappingRepository.findByTaskId(id);
        Integer shipmentId;
        GetTripSheetRequest getTripSheetRequest = new GetTripSheetRequest();
        List<GetTripSheetRequest.ShipmentDetails> shipmentDetailses = new ArrayList<>();
        DeliverShipmentRequest deliverShipmentRequest = null;
        GetTripSheetRequest.ShipmentDetails shipmentDetails;
        PickupRequestResponse pickupRequest;
        for ( TaskIdShipmentMapping taskIdShipmentMapping : taskIdShipmentMappings) {
            shipmentId = taskIdShipmentMapping.getShipmentId();
            if("create_runsheet".equalsIgnoreCase(task.getTaskDescription())) {
                deliverShipmentRequest = deliverShipmentRequestRepository.findByShipmentId(shipmentId);
                shipmentDetails = new GetTripSheetRequest.ShipmentDetails(shipmentId,deliverShipmentRequest.getAddress(),deliverShipmentRequest.getPincode());
            }else {
                pickupRequest = pickupRequestRepository.findByShipmentId(shipmentId);
                shipmentDetails = new GetTripSheetRequest.ShipmentDetails(shipmentId,pickupRequest.getAddress(),pickupRequest.getPincode());
            }
            shipmentDetailses.add(shipmentDetails);
        }
        getTripSheetRequest.setShipmentDetails(shipmentDetailses);
        getTripSheetRequest.setTripSheetId(id);


        return new ResponseEntity<>(getTripSheetRequest,HttpStatus.ACCEPTED);
    }


}
