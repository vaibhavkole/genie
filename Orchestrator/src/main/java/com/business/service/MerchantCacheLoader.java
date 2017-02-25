package com.business.service;

import com.business.dto.MerchantDto;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by vaibhav.janardhan on 25/02/17.
 */
@Component
@Slf4j
public class MerchantCacheLoader {
    private final CacheLoader<String, MerchantDto> cacheLoader;

    private final LoadingCache<String, MerchantDto> merchantCache;

    @Autowired
    public MerchantCacheLoader(CacheLoader cacheLoader) {
        this.cacheLoader = cacheLoader;

        merchantCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .build(cacheLoader);

    }

    public MerchantDto getMerchantInfo(String merchantName) {
        MerchantDto model = null;
        try {
            model = merchantCache.get(merchantName);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return model;
    }

}
