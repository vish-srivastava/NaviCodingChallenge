package com.navi.codingchallenge.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Loan {
    private String bankName;
    private String borrowerName;
    private Double principle;
    private Integer years;
    private Double rateOfInterest;
    private InterestType interestType;
    @Setter
    private Double totalRepayableAmount;
    @Setter
    private Double monthlyEMIAmount;
    @Setter
    private Double lumpSumPaid;
    private LoanType loanType;

}


