package com.business.service;

import com.business.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

    public HttpRequestHandler(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<MerchantDto> getAllMerchants() {
        ResponseEntity<List<MerchantDto>> rateResponse =
                restTemplate.exchange("http://localhost:29000/merchant",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<MerchantDto>>() {
                        });
        return rateResponse.getBody();
    }

    public MerchantDto getMerchantByName(String merchantName) {
        ResponseEntity<MerchantDto> rateResponse =
                restTemplate.exchange("http://localhost:29000/merchant?name=" + merchantName,
                        HttpMethod.GET, null, MerchantDto.class);
        return rateResponse.getBody();
    }

    public MerchantDto getMerchantById(Integer merchantId) {
        ResponseEntity<MerchantDto> rateResponse =
                restTemplate.exchange("http://localhost:29000/merchant/" + merchantId,
                        HttpMethod.GET, null, MerchantDto.class);
        return rateResponse.getBody();
    }


    public Task createRunsheet(List<Integer> shipmentIdList, Integer locationId) {
        CreateSheetModel createSheetModel = new CreateSheetModel();
        createSheetModel.setLocationId(locationId);
        createSheetModel.setShipmentIds(shipmentIdList);
        createSheetModel.setTaskDescription("create_runsheet");
        ResponseEntity<Task> responseEntity =
                restTemplate.postForEntity("http://localhost:29011/task",
                        createSheetModel, Task.class);
        return responseEntity.getBody();

    }

    public PickupRequestResponse createHttpPickupRequest(CreatePickupRequest createPickupRequest, int locationId) {
        ResponseEntity<PickupRequestResponse> responseEntity =
                restTemplate.postForEntity("http://localhost:29011/task/create_pickup_request/" + locationId,
                        createPickupRequest, PickupRequestResponse.class);
        return responseEntity.getBody();
    }

    public DeliverShipmentRequest createHttpDeliverRequest(DeliverShipmentRequest deliverShipmentRequest, int locationId) {
        ResponseEntity<DeliverShipmentRequest> responseEntity =
                restTemplate.postForEntity("http://localhost:29011/task/create_pickup_request/" + locationId,
                        deliverShipmentRequest, DeliverShipmentRequest.class);
        return responseEntity.getBody();
    }





    //not required
    public Task createPickUpSheet(List<Integer> shipmentIdList, Integer locationId) {
        CreateSheetModel createSheetModel = new CreateSheetModel();
        createSheetModel.setLocationId(locationId);
        createSheetModel.setShipmentIds(shipmentIdList);
        createSheetModel.setTaskDescription("create_pickupsheet");
        ResponseEntity<Task> responseEntity =
                restTemplate.postForEntity("http://localhost:29011/task",
                        createSheetModel, Task.class);
        return responseEntity.getBody();

    }

    //not required
    public List<String> mark_runsheet_complete(Integer runsheetId) {
        Class<List<String>> clazz = (Class) List.class;
        ResponseEntity<List<String>> responseEntity =
                restTemplate.postForEntity("http://localhost:29011/task/mark_runsheet_complete/"+ runsheetId,
                        null,clazz);
        return responseEntity.getBody();
    }

    //not required
    public List<String> mark_pickup_sheet_complete(Integer pickupSheetId) {
        Class<List<String>> clazz = (Class) List.class;
        ResponseEntity<List<String>> responseEntity =
                restTemplate.postForEntity("http://localhost:29011/task/mark_pickup_sheet_complete/"+ pickupSheetId,
                        null,clazz);
        return responseEntity.getBody();
    }



}
