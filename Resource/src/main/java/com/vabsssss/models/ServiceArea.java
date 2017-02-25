package com.vabsssss.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by heena.h on 26/02/17.
 */

@Entity
public class ServiceArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer hubId;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHub_id() {
        return hubId;
    }

    public void setHub_id(Integer hub_id) {
        this.hubId = hub_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ServiceArea{" +
                "id=" + id +
                ", hub_id='" + hubId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
