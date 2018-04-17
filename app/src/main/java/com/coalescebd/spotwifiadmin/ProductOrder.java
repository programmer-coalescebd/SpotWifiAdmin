package com.coalescebd.spotwifiadmin;

public class ProductOrder {

    private String orderName, orderPhone, orderDeliveryLocation, orderEmail, statusOfDelivery, orderProduct, orderPrice;
    private int currentBookingId;

    public ProductOrder() {
    }

    public ProductOrder(String orderName, String orderPhone, String orderEmail, String orderDeliveryLocation, String statusOfDelivery, String orderProduct, String orderPrice, int currentBookingId) {
        this.orderName = orderName;
        this.orderPhone = orderPhone;
        this.orderEmail = orderEmail;
        this.orderDeliveryLocation=orderDeliveryLocation;
        this.statusOfDelivery=statusOfDelivery;
        this.orderProduct=orderProduct;
        this.orderPrice=orderPrice;
        this.currentBookingId=currentBookingId;
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

    public String getOrderEmail() {
        return orderEmail;
    }

    public void setOrderEmail(String orderEmail) {
        this.orderEmail = orderEmail;
    }

    public String getOrderDeliveryLocation() {
        return orderDeliveryLocation;
    }

    public void setOrderDeliveryLocation(String orderDeliveryLocation) {
        this.orderDeliveryLocation = orderDeliveryLocation;
    }

    public String getStatusOfDelivery() {
        return statusOfDelivery;
    }

    public void setStatusOfDelivery(String statusOfDelivery) {
        this.statusOfDelivery = statusOfDelivery;
    }

    public String getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(String orderProduct) {
        this.orderProduct = orderProduct;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCurrentBookingId() {
        return currentBookingId;
    }

    public void setCurrentBookingId(int currentBookingId) {
        this.currentBookingId = currentBookingId;
    }
}




