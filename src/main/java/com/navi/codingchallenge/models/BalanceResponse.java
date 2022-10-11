package com.navi.codingchallenge.models;

import lombok.Builder;
import lombok.Getter;

import static com.navi.codingchallenge.models.Constants.DELIMITER;

@Builder
public class BalanceResponse {

    private String bankName;
    private String borrowerName;
    private int amountRepaid;
    private int remainingEmis;


    @Getter
    private int outstandingAmount;


    @Override
    public String toString() {
        return this.bankName + DELIMITER +
                this.borrowerName + DELIMITER +
                this.amountRepaid + DELIMITER +
                this.remainingEmis;
    }
}
