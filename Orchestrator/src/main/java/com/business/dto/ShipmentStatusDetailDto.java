package com.business.dto;

import com.business.models.Shipment;
import com.business.models.Status;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
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

    /*private Shipment shipment;
    private Status status;
    private Integer pickupHubId;
    private Integer deliveryHubId;
    private Integer currentHubId;
    private Timestamp createdAt;
    private Timestamp updatedAt;*/
}


