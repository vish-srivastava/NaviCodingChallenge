package com.navi.codingchallenge.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Loan {
    private String bankName;
    private String borrowerName;
    private Double principle;
    private Integer years;
    private Double rateOfInterest;
    private Double amount;
    private Double monthlyEMIAmount;
    private Double lumpSumPaid;

    public void processLoan() {
        Double interest = this.principle * (this.rateOfInterest) * this.years;
        this.amount = this.principle + interest;
        this.monthlyEMIAmount = Math.ceil(this.amount / (12 * this.years));
        this.lumpSumPaid = 0.0;
    }

    public void acceptLumpSumPayment(Double repaymentAmount) {
        this.lumpSumPaid += repaymentAmount;
    }


    public Integer getRemainingEMIs(Integer emisPaid) {
        Double outstandingAmount = this.amount - (this.lumpSumPaid + this.monthlyEMIAmount * emisPaid);
        return (int) Math.ceil(outstandingAmount / this.monthlyEMIAmount);
    }

    public Integer getAmountRepaid(Integer emisPaid) {
        return (int) Math.ceil(this.lumpSumPaid + this.monthlyEMIAmount * emisPaid);
    }
}


