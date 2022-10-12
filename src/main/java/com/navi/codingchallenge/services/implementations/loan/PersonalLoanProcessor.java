package com.navi.codingchallenge.services.implementations.loan;

import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.services.interfaces.LoanProcessor;
import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.exceptions.NotImplementedException;
import com.navi.codingchallenge.models.LoanApplicationRequest;
import com.navi.codingchallenge.models.LoanType;
import lombok.Getter;

public class PersonalLoanProcessor implements LoanProcessor {

    @Getter
    private static final LoanType loanType = LoanType.PERSONAL;

    @Override
    public Loan processLoanRequest(LoanApplicationRequest request) throws InvalidInputException, NotImplementedException {
        throw new NotImplementedException();
    }
}
