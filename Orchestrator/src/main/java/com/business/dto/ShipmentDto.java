package com.business.dto;

import com.business.models.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Data
public class ShipmentDto {
    @JsonProperty(value = "merchant_name")
    private String merchantName;
    @JsonProperty(value = "shipment_ref_number")
    private String shipmentRefNumber;
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
