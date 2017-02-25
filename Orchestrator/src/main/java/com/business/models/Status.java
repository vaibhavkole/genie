package com.business.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Entity
@Table(name = "Status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String status;
    @Column(insertable = false)
    private Timestamp createdAt;
}
