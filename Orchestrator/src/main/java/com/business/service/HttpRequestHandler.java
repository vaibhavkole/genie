package com.business.service;

import com.business.dto.MerchantDto;
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




}
