package com.genie.transport.service;

import com.genie.transport.model.Vendor;
import com.genie.transport.repository.IVendorRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
@Service
public class VendorService implements IVendorService{

    private final IVendorRepository repository;

    @Autowired
    public VendorService(IVendorRepository repository) {
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int createVendor(@NonNull String name, @NonNull String address) {
        Vendor vendor = new Vendor(name, address);
        repository.save(vendor);
        return vendor.getId();
    }

    @Override
    public Vendor getVendor(int id) {
        return repository.findOne(id);
    }

}
