package com.business.cont;

import com.business.dto.MerchantDto;
import com.business.mapper.MerchantMapper;
import com.business.models.Merchant;
import com.business.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vaibhav.janardhan on 25/02/17.
 */

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private MerchantMapper merchantMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addMerchant(@RequestBody MerchantDto merchantDto) {
        Merchant merchant = merchantMapper.convertToEntity(merchantDto);
        Merchant createdTenant = merchantService.addMerchant(merchant);
        return new ResponseEntity<>(merchantMapper.convertToDto(createdTenant), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getTenantByName() {
        List<Merchant> merchants = merchantService.getAllMerchant();
        return new ResponseEntity<>(merchants.stream()
                .map(tenant -> merchantMapper.convertToDto(tenant))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity<?> findByMerchantName(@RequestParam(value="name") String merchantName) {
        Merchant merchant = merchantService.getMerchantByName(merchantName);
        return new ResponseEntity<>(merchantMapper.convertToDto(merchant), HttpStatus.OK);
    }
}
