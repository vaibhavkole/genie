package com.genie.transport.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
@Entity
@Data
@NoArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique=true)
    private String name;

    private String address;

    @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
    private List<Connection> connections;

    private Date updateDateTime;

    public Vendor(String name, String address) {
        this.name = name;
        this.address = address;
        this.updateDateTime = new Date();
    }

}
