package com.navi.codingchallenge.services.interfaces;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.exceptions.NotImplementedException;
import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.models.LoanApplicationRequest;

public interface LoanProcessor {

    /**
     * Process a loan request and save loan
     * @param request
     * @throws InvalidInputException
     * @throws NotImplementedException
     */
    Loan processLoanRequest(LoanApplicationRequest request) throws InvalidInputException, NotImplementedException;
}
