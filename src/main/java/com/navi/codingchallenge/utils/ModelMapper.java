package com.navi.codingchallenge.utils;

import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.models.LoanApplicationRequest;

import static com.navi.codingchallenge.models.Constants.ONE_HUNDRED;

public class ModelMapper {

    private ModelMapper() {
        // do nothing
    }

    public static Loan mapLoanApplicationRequestToLoan(LoanApplicationRequest request) {
        return Loan.builder()
                .bankName(request.getBankName())
                .borrowerName(request.getBorrowerName())
                .principle(request.getPrinciple())
                .years(request.getNumberOfYears())
                .loanType(request.getLoanType())
                .interestType(request.getInterestType())
                .rateOfInterest((request.getRateOfInterest() / ONE_HUNDRED))
                .repaymentFrequency(request.getRepaymentFrequency())
                .build();
    }
}
