package com.genie.transport.service;

import com.genie.transport.dto.ConnectionCreateRequest;
import com.genie.transport.dto.PartnerCreationRequest;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
public interface IPartnerService {

    int createPartner(PartnerCreationRequest request);

    int createConnection(ConnectionCreateRequest request);

}
