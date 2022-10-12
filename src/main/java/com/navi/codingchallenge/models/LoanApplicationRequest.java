package com.navi.codingchallenge.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoanApplicationRequest {
    private String bankName;
    private String borrowerName;
    private Double principle;
    private Integer numberOfYears;
    private Double rateOfInterest;
    private LoanType loanType=LoanType.GENERIC;
    private InterestType interestType=InterestType.FIXED_SIMPLE_INTEREST;
    private RepaymentFrequency repaymentFrequency=RepaymentFrequency.MONTHLY;
}
