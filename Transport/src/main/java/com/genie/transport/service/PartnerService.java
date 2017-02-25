package com.genie.transport.service;

import com.genie.transport.dto.ConnectionCreateRequest;
import com.genie.transport.dto.PartnerCreationRequest;
import com.genie.transport.model.Vendor;
import java.util.Date;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
@Service
public class PartnerService  implements IPartnerService{


    private final IVendorService vendorService;

    private final IConnectionService connectionService;

    @Autowired
    public PartnerService(IVendorService vendorService, IConnectionService connectionService) {
        this.vendorService = vendorService;
        this.connectionService = connectionService;
    }

    @Override
    public int createPartner(@NonNull PartnerCreationRequest request) {
        return vendorService.createVendor(request.getName(), request.getAddress());
    }

    @Override
    public int createConnection(ConnectionCreateRequest request) {
        Date transitTime = new Date(request.getTransitTime());
        Vendor vendor = vendorService.getVendor(request.getPartnerId());
        return connectionService.createConnection(request.getSourceHub(),
                request.getDestinationHub(), request.getCost(), request.getCapacity(),
                transitTime, vendor, request.getVendorConnectionId());
    }
}
