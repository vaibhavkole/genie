package com.business.models;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Entity
@Table(name = "ShipmentStatusDetail")
@Data
public class ShipmentStatusDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(fetch = FetchType.EAGER)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;
    @Column(nullable = false)
    private Integer pickupHubId;
    @Column(nullable = false)
    private Integer deliveryHubId;
    @Column(nullable = false)
    private Integer currentHubId;
    @Column(insertable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(insertable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;
}
