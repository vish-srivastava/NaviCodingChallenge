package com.navi.codingchallenge.models;

public class Distribution {
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double perecentage) {
        this.percentage = perecentage;
    }

    public void setMonthlySipAmount(Double monthlySipAmount) {
        if (this.monthlySipAmount == null) {
            this.monthlySipAmount = 0.0;
        }
        this.monthlySipAmount += monthlySipAmount;
    }

    Double amount;
    Double percentage;
    Double monthlySipAmount;

    Integer sipCount;

    public Integer getSipCount() {
        return sipCount;
    }

    public void incrementSIPCount() {
        this.sipCount++;
    }

    public Distribution(Double amount, Double percentage) {
        this.amount = amount;
        this.percentage = percentage;
        this.sipCount = 0;
    }
}
