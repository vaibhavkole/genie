package com.vabsssss.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by heena.h on 26/02/17.
 */

@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer hubId;
    private Integer status;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHubId() {
        return hubId;
    }

    public void setHubId(Integer hubId) {
        this.hubId = hubId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", hubId='" + hubId + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
