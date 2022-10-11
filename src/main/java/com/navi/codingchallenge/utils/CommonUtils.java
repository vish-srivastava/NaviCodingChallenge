package com.navi.codingchallenge.utils;

import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.models.LoanApplicationRequest;

import static com.navi.codingchallenge.models.Constants.ONE_HUNDRED;

public class CommonUtils {

    private CommonUtils(){
        // do nothing
    }
    public static Loan mapLoanApplicationRequestToLoan(LoanApplicationRequest request) {
        return Loan.builder().bankName(request.getBankName()).borrowerName(request.getBorrowerName()).principle(request.getPrinciple()).years(request.getNumberOfYears()).rateOfInterest((request.getRateOfInterest() / ONE_HUNDRED)).build();
    }
}
