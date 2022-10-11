package com.navi.codingchallenge.components.implementations;

import com.navi.codingchallenge.components.implementations.loanprocessors.CarLoanProcessor;
import com.navi.codingchallenge.components.implementations.loanprocessors.EducationLoanProcessor;
import com.navi.codingchallenge.components.implementations.loanprocessors.GenericLoanProcessor;
import com.navi.codingchallenge.components.implementations.loanprocessors.HomeLoanProcessor;
import com.navi.codingchallenge.components.interfaces.LoanProcessor;
import com.navi.codingchallenge.models.LoanType;

public class LoanProcessingFactory {

    private LoanProcessingFactory(){
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
                return new HomeLoanProcessor();

            default:
                return new GenericLoanProcessor();


        }
    }
}
