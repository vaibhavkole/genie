package com.business.dto;

import com.business.models.Shipment;
import com.business.models.Status;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Data
public class ShipmentStatusDetailDto {
    @JsonProperty(value = "shipment_ref_number")
    private String shipmentRefNumber;
    @JsonProperty(value = "status")
    private String status;
    @JsonProperty(value = "pickup_hub_id")
    private Integer pickupHubId;
    @JsonProperty(value = "delivery_hub_id")
    private Integer deliveryHubId;
    @JsonProperty(value = "current_hub_id")
    private Integer currentHubId;
    @JsonProperty(value = "created_at")
    private String createdAt;
    @JsonProperty(value = "updated_at")
    private String updatedAt;
}


