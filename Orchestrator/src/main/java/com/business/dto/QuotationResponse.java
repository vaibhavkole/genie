package com.business.dto;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
public class QuotationResponse {
    double cost;
    int days;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
