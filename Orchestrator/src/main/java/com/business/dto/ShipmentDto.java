package com.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Data
public class ShipmentDto {
    @JsonProperty(value = "merchant_name")
    private String merchantName;
    @JsonProperty(value = "shipment_ref_id")
    private String shipmentRefId;
    @JsonProperty(value = "pickup_address")
    private AddressDto pickupAddress;
    @JsonProperty(value = "delivery_address")
    private AddressDto deliveryAddress;
    @JsonProperty(value = "volumetric_weight")
    private Double volumetricWeight;
    @JsonProperty(value = "created_at")
    private String createdAt;
    @JsonProperty(value = "updated_at")
    private String updatedAt;
}
