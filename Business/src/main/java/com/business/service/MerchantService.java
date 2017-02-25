package com.business.service;

import com.business.models.Merchant;
import com.business.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vaibhav.janardhan on 25/02/17.
 */
@Service
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    public Merchant addMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Transactional(readOnly = true)
    public List<Merchant> getAllMerchant() {
        return (List<Merchant>) this.merchantRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Merchant getMerchantByName(String merchantName){
        return this.merchantRepository.findByMerchantName(merchantName);
    }
    public Merchant getMerchantById(Integer id){
        return this.merchantRepository.findOne(id);
    }

}