package com.vabsssss.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by heena.h on 26/02/17.
 */

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer hubId;
    private Date reservationDate;
    private Double reservationCapacity;
    private Integer vehicleId;

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

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Double getReservationCapacity() {
        return reservationCapacity;
    }

    public void setReservationCapacity(Double reservationCapacity) {
        this.reservationCapacity = reservationCapacity;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return "Reservation{" +
                "id=" + id +
                ", hubId='" + hubId + '\'' +
                ", reservationDate='" + df.format(reservationDate) + '\'' +
                ", reservationCapacity='" + reservationCapacity + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                '}';
    }
}
