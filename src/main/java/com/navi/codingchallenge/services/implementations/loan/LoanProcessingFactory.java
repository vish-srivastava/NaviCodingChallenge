package com.navi.codingchallenge.services.implementations.loan;

import com.navi.codingchallenge.services.interfaces.LoanProcessor;
import com.navi.codingchallenge.models.LoanType;

public class LoanProcessingFactory {

    private LoanProcessingFactory() {
        // hide public constructor
    }

    public static LoanProcessor getLoanProcessorByLoanType(LoanType loanType) {
        switch (loanType) {
            case HOME:
                return new HomeLoanProcessor();

            case CAR:
                return new CarLoanProcessor();

            case EDUCATIONAL:
                return new EducationLoanProcessor();

            case PERSONAL:
                return new PersonalLoanProcessor();

            default:
                return new GenericLoanProcessor();


        }
    }
}
