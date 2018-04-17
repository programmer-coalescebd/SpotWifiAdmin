package com.coalescebd.spotwifiadmin;

public class orders {
    String orderDeliveryLocation,orderName, orderPhone, statusOfDelivery;

    public orders() {
    }

    public orders(String orderDeliveryLocation, String orderName, String orderPhone, String statusOfDelivery) {
        this.orderDeliveryLocation = orderDeliveryLocation;
        this.orderName = orderName;
        this.orderPhone = orderPhone;
        this.statusOfDelivery = statusOfDelivery;
    }

    public String getOrderDeliveryLocation() {
        return orderDeliveryLocation;
    }

    public void setOrderDeliveryLocation(String orderDeliveryLocation) {
        this.orderDeliveryLocation = orderDeliveryLocation;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getStatusOfDelivery() {
        return statusOfDelivery;
    }

    public void setStatusOfDelivery(String statusOfDelivery) {
        this.statusOfDelivery = statusOfDelivery;
    }
}
