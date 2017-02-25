package com.genie.transport.dto;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
@Data
public class PartnerCreationRequest {
    @NonNull
    private String name;

    @NonNull
    private String address;
}
