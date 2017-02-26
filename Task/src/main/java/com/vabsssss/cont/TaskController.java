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
    CreateExpectationForShipmentRepository createExpectationForShipmentRepository;

    @Autowired
    DeliverShipmentRequestRepository deliverShipmentRequestRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createTaskSheet(@RequestBody CreateSheetModel createSheetModel) {
        //return new ResponseEntity<>(tempRepository.save(input), HttpStatus.CREATED);
        return new ResponseEntity<>(taskService.createTripSheet(createSheetModel), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/receive_shipment/{shipment_id}/{location_id}",method = RequestMethod.GET)
    public ResponseEntity<?> receiveShipment(@PathVariable Integer shipment_id, @PathVariable Integer location_id) {
        //return new ResponseEntity<>(tempRepository.save(input), HttpStatus.CREATED);
        return new ResponseEntity<>(taskService.receiveShipment(shipment_id,location_id), HttpStatus.ACCEPTED);
    }



    @RequestMapping(value = "/sendPickupEventCompleted/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> sendPickupEventCompleted(@PathVariable Integer id) {


        return new ResponseEntity<>(taskService.sendPickupEventCompleted(id),HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/sendDeliveredEventCompleted/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> sendDeliveredEventCompleted(@PathVariable Integer id) {


        return new ResponseEntity<>(taskService.sendDeliveredEventCompleted(id),HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/forward_shipment",method = RequestMethod.POST)
    public ResponseEntity<?> forwardShipment(@RequestBody ForwardShipment forwardShipment) {
        //return new ResponseEntity<>(tempRepository.save(input), HttpStatus.CREATED);
        return new ResponseEntity<>(forwardShipmentRepository.save(forwardShipment), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/expect_shipment",method = RequestMethod.POST)
    public ResponseEntity<?> expectShipment(@RequestBody CreateExpectationForShipment createExpectationForShipment) {
        //return new ResponseEntity<>(tempRepository.save(input), HttpStatus.CREATED);
        createExpectationForShipment.setDate(new Date());
        return new ResponseEntity<>(createExpectationForShipmentRepository.save(createExpectationForShipment), HttpStatus.CREATED);
    }

    //not required
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

    //not required
    @RequestMapping(value = "/mark_pickup_sheet_complete/{sheet}",method = RequestMethod.POST)
    public ResponseEntity<?> markPickupSheetComplete(@PathVariable Integer sheet) {
        Task task = taskRepository.findOne(sheet);
        task.setActive(false);
        task.setDate(new Date());
        taskRepository.save(task);
        List<TaskIdShipmentMapping> taskIdShipmentMappings = taskIdShipmentMappingRepository.findByTaskId(sheet);
        List<Integer> shipmentIdPickup = new ArrayList<>();
        for(TaskIdShipmentMapping taskIdShipmentMapping : taskIdShipmentMappings) {
            shipmentIdPickup.add(taskIdShipmentMapping.getShipmentId());
        }
        return new ResponseEntity<>(shipmentIdPickup,HttpStatus.FOUND);
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


        GetTripSheetRequest getTripSheetRequest = taskService.getTripSheetRequest(id);
                return new ResponseEntity<>(getTripSheetRequest,HttpStatus.ACCEPTED);
    }



}
