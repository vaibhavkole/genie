package com.business.cont;

import com.business.dto.MerchantDto;
import com.business.service.HttpRequestHandler;
import com.business.service.MerchantCacheLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vaibhav.janardhan on 25/02/17.
 */

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private HttpRequestHandler httpRequestHandler;

    @Autowired
    private MerchantCacheLoader merchantCacheLoader;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getMechants(){
        List<MerchantDto> merchants = httpRequestHandler.getAllMerchants();
        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity<?> findByMerchantName(@RequestParam(value="name") String merchantName) {
        MerchantDto merchantInfo = merchantCacheLoader.getMerchantInfo(merchantName);
        return new ResponseEntity<>(merchantInfo, HttpStatus.OK);
    }

}