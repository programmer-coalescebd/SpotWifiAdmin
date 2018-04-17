package com.coalescebd.spotwifiadmin;

public class SalesPerson {
    String salesPersonName, salesPersonPhone, salesPersonEmail, salesPersonPass, salesLocation;

    public SalesPerson(String salesPersonName, String salesPersonPhone, String salesPersonEmail, String salesPersonPass ,String salesLocation) {
        this.salesPersonName = salesPersonName;
        this.salesPersonPhone = salesPersonPhone;
        this.salesPersonEmail = salesPersonEmail;
        this.salesPersonPass = salesPersonPass;
        this.salesLocation = salesLocation;
    }

    public SalesPerson() {
    }

    public String getSalesPersonName() {
        return salesPersonName;
    }

    public void setSalesPersonName(String salesPersonName) {
        this.salesPersonName = salesPersonName;
    }

    public String getSalesPersonPhone() {
        return salesPersonPhone;
    }

    public void setSalesPersonPhone(String salesPersonPhone) {
        this.salesPersonPhone = salesPersonPhone;
    }

    public String getSalesPersonEmail() {
        return salesPersonEmail;
    }

    public String getSalesLocation() {
        return salesLocation;
    }

    public void setSalesLocation(String salesLocation) {
        this.salesLocation = salesLocation;
    }

    public void setSalesPersonEmail(String salesPersonEmail) {
        this.salesPersonEmail = salesPersonEmail;

    }

    public String getSalesPersonPass() {
        return salesPersonPass;
    }

    public void setSalesPersonPass(String salesPersonPass) {
        this.salesPersonPass = salesPersonPass;
    }
}
