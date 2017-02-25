package com.genie.transport.service;

import com.genie.transport.model.Vendor;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
public interface IVendorService {

    int createVendor(String name, String address);

    Vendor getVendor(int id);

}
