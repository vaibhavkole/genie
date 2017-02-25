package com.business.models;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Entity
@Table(name = "Address")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String pincode;
    private String city;
    private String state;
    private String primaryContactNumber;
    private String alternateContactNumber;
    private String landmark;
    private String emailId;
    @Column(insertable = false)
    private Timestamp createdAt;
    @Column(insertable = false)
    private Timestamp updatedAt;
}
