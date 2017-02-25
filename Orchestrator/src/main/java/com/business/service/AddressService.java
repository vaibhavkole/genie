package com.business.service;

import com.business.models.Address;
import com.business.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }
}
