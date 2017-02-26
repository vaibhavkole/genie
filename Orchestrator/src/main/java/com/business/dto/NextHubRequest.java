package com.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by akashdeep.saluja on 26/02/17.
 */
@Data
@AllArgsConstructor
public class NextHubRequest {

    int shipmentId;

    int hub;

}
