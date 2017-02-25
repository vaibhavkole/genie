package com.business.models;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by vaibhav.janardhan on 25/02/17.
 */
@Entity
@Data
@Table(name = "Merchant")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String merchantName;
    @Column(nullable = false)
    private String authCode;
    private String description;
    @Column(insertable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(insertable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

}
