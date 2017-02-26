package com.vabsssss.service;

import com.vabsssss.dto.CreateSheetModel;
import com.vabsssss.dto.GetTripSheetRequest;
import com.vabsssss.models.DeliverShipmentRequest;
import com.vabsssss.models.PickupRequestResponse;
import com.vabsssss.models.Task;
import com.vabsssss.models.TaskIdShipmentMapping;
import com.vabsssss.repository.DeliverShipmentRequestRepository;
import com.vabsssss.repository.PickupRequestRepository;
import com.vabsssss.repository.TaskIdShipmentMappingRepository;
import com.vabsssss.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dheeraj.mittal on 25/02/17.
 */

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    DeliverShipmentRequestRepository deliverShipmentRequestRepository;

    @Autowired
    PickupRequestRepository pickupRequestRepository;


    @Autowired
    TaskIdShipmentMappingRepository taskIdShipmentMappingRepository;
    @Autowired
    HttpRequestHandler httpRequestHandler;


    public Task createTripSheet(CreateSheetModel createSheetModel) {
        Task task = new Task();
        task.setLocationId(createSheetModel.getLocationId());
        task.setTaskDescription(createSheetModel.getTaskDescription());
        task.setDate(new Date());
        task.setActive(true);
        task = taskRepository.save(task);
        createShipmentRunsheetMapping(task,createSheetModel);
        return task;
    }

    private void createShipmentRunsheetMapping(Task task, CreateSheetModel createSheetModel) {
        TaskIdShipmentMapping taskIdShipmentMapping;
        for(Integer shipmentId : createSheetModel.getShipmentIds()) {
            taskIdShipmentMapping = new TaskIdShipmentMapping();
            taskIdShipmentMapping.setShipmentId(shipmentId);
            taskIdShipmentMapping.setTaskId(task.getId());
            taskIdShipmentMapping.setActive(true);
            taskIdShipmentMappingRepository.save(taskIdShipmentMapping);
        }
    }

    public GetTripSheetRequest getTripSheetRequest(Integer id) {
        Task task = taskRepository.findOne(id);
        List<TaskIdShipmentMapping> taskIdShipmentMappings = taskIdShipmentMappingRepository.findByTaskId(id);
        GetTripSheetRequest getTripSheetRequest = new GetTripSheetRequest();
        List<GetTripSheetRequest.ShipmentDetails> shipmentDetailses = new ArrayList<>();
        DeliverShipmentRequest deliverShipmentRequest = null;
        GetTripSheetRequest.ShipmentDetails shipmentDetails;
        PickupRequestResponse pickupRequest;
        Integer shipmentId;
        for ( TaskIdShipmentMapping taskIdShipmentMapping : taskIdShipmentMappings) {
            shipmentId = taskIdShipmentMapping.getShipmentId();
            if("create_runsheet".equalsIgnoreCase(task.getTaskDescription())) {
                deliverShipmentRequest = new DeliverShipmentRequest();
                deliverShipmentRequest.setShipmentId(shipmentId);
                deliverShipmentRequest = deliverShipmentRequestRepository.save(deliverShipmentRequest);
                shipmentDetails = new GetTripSheetRequest.ShipmentDetails(shipmentId,deliverShipmentRequest.getAddress(),deliverShipmentRequest.getPincode());
            }else {
                pickupRequest = pickupRequestRepository.findByShipmentId(shipmentId);
                shipmentDetails = new GetTripSheetRequest.ShipmentDetails(shipmentId,pickupRequest.getAddress(),pickupRequest.getPincode());
            }
            shipmentDetailses.add(shipmentDetails);
        }
        getTripSheetRequest.setShipmentDetails(shipmentDetailses);
        getTripSheetRequest.setTripSheetId(id);
        return getTripSheetRequest;
    }

    public Integer sendPickupEventCompleted(Integer picksheetId) {
        return httpRequestHandler.sendPickupEventCompleted(picksheetId);
    }
    public Integer sendDeliveredEventCompleted(Integer runsheetId) {
        return httpRequestHandler.sendDeliveredEventCompleted(runsheetId);
    }

    public Integer receiveShipment(Integer shipmentId, Integer locationId) {
        return httpRequestHandler.receiveShipment(shipmentId,locationId);
    }
}
