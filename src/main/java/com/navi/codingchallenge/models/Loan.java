package com.navi.codingchallenge.models;

import lombok.Builder;
import lombok.Getter;

@Builder
public class Loan {

    private String bankName;

    private String borrowerName;
    private Double principle;
    private Integer years;
    private Double rateOfInterest;
    private Double amount;
    private Double monthlyEMIAmount;
    private Double lumpSumPaid;

    private static final Integer monthsInYear = 12;

    public String getBorrowersBankName() {
        return this.bankName;
    }

    public String getBorrowersName() {
        return this.borrowerName;
    }

    public void processLoan() {
        Double interest = this.principle * (this.rateOfInterest) * this.years;
        this.amount = this.principle + interest;
        this.monthlyEMIAmount = Math.ceil(this.amount / (monthsInYear * this.years));
        this.lumpSumPaid = 0.0;
    }

    public void acceptLumpSumPayment(Double repaymentAmount) {
        this.lumpSumPaid += repaymentAmount;
    }


    public Integer getRemainingEMIs(Integer emisPaid) {
        Double outstandingAmount = this.amount - (this.lumpSumPaid + this.monthlyEMIAmount * emisPaid);
        int remainingEmis = (int) Math.ceil(outstandingAmount / this.monthlyEMIAmount);
        return Math.max(remainingEmis, 0);
    }

    public Integer getAmountRepaid(Integer emisPaid) {
        return (Integer) (int) Math.min((Math.ceil(this.lumpSumPaid + this.monthlyEMIAmount * emisPaid)), this.amount);
    }
}


