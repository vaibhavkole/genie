package com.business.service;

import com.business.dto.MerchantDto;
import com.google.common.cache.CacheLoader;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Component
public class MerchantInfoCacheLoader extends CacheLoader<String, MerchantDto> {

    private final HttpRequestHandler requestHandler;

    @Autowired
    public MerchantInfoCacheLoader(HttpRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public MerchantDto load(String merchantName) throws Exception {
        return requestHandler.getMerchantByName(merchantName);
    }
}