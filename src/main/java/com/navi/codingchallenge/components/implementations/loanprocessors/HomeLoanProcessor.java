package com.navi.codingchallenge.components.implementations.loanprocessors;

import com.navi.codingchallenge.components.interfaces.LoanProcessor;
import com.navi.codingchallenge.exceptions.NotImplementedException;
import com.navi.codingchallenge.models.LoanApplicationRequest;
import com.navi.codingchallenge.models.LoanType;
import lombok.Getter;

public class HomeLoanProcessor implements LoanProcessor {

    @Getter
    private static final LoanType loanType = LoanType.HOME;

    @Override
    public void processLoanRequest(LoanApplicationRequest request) throws NotImplementedException {
        throw new NotImplementedException();
    }
}
