package com.business.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Entity
@Table(name = "Shipment")
@Data
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private Integer merchantId;
    @Column(nullable = false)
    private String shipmentRefNumber;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pickup_address_id")
    private Address pickupAddress;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;
    @Column(nullable = false)
    private Double volumetricWeight;
    @Column(insertable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(insertable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;
}
