package com.vabsssss.service;

import com.vabsssss.cont.TaskController;
import com.vabsssss.dto.GetTripSheetRequest;
import com.vabsssss.models.Task;
import com.vabsssss.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

/**
 * Created by vaibhav.janardhan on 25/02/17.
 */
@Scope(value = SCOPE_SINGLETON)
@Slf4j
@Component
public class HttpRequestHandler {

    private final RestTemplate restTemplate;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    public HttpRequestHandler(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Integer sendPickupEventCompleted(Integer pickupSheetId) {
        List<String> shipmentIds = new ArrayList<>();
        TaskController taskController = new TaskController();

        Task task = taskRepository.findOne(pickupSheetId);
        GetTripSheetRequest getTripSheetRequest = taskService.getTripSheetRequest(pickupSheetId);
        for(GetTripSheetRequest.ShipmentDetails shipmentDetails: getTripSheetRequest.getShipmentDetails()) {
            ResponseEntity<Integer> responseEntity =
                    restTemplate.postForEntity("http://localhost:29001/shipment/" +
                                    "pickup_complete/" + shipmentDetails.getShipmentId()+ "/" + task.getLocationId(),
                            null, Integer.class);
            System.out.println("sendPickupEventCompleted" +responseEntity);
        }
        return 1;
    }

    public Integer sendDeliveredEventCompleted(Integer runsheetId) {
        List<String> shipmentIds = new ArrayList<>();
        TaskController taskController = new TaskController();

        Task task = taskRepository.findOne(runsheetId);
        GetTripSheetRequest getTripSheetRequest = taskService.getTripSheetRequest(runsheetId);
        for(GetTripSheetRequest.ShipmentDetails shipmentDetails: getTripSheetRequest.getShipmentDetails()) {
            ResponseEntity<Integer> responseEntity =
                    restTemplate.postForEntity("http://localhost:29001/shipment/" +
                                    "delivered/" + shipmentDetails.getShipmentId()+ "/" + task.getLocationId(),
                            null, Integer.class);
        }
        return 1;
    }

    public Integer receiveShipment(Integer shipmentId, Integer locationId) {
        ResponseEntity<Integer> responseEntity =
                restTemplate.postForEntity("http://localhost:29001/shipment/" +
                                "received/" + shipmentId+ "/" + locationId,
                        null, Integer.class);
        return null;
    }
}
