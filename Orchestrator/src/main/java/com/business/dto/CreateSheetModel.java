package com.business.dto;

import java.util.List;

/**
 * Created by dheeraj.mittal on 25/02/17.
 */

public class CreateSheetModel {

    private List<Integer> shipmentIds;
    private String taskDescription;
    private Integer locationId;

    public List<Integer> getShipmentIds() {
        return shipmentIds;
    }

    public void setShipmentIds(List<Integer> shipmentIds) {
        this.shipmentIds = shipmentIds;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }
}
