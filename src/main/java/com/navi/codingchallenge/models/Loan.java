package com.navi.codingchallenge.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
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

}


