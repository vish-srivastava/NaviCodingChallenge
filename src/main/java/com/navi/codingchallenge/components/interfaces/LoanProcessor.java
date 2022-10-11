package com.navi.codingchallenge.components.interfaces;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.exceptions.NotImplementedException;
import com.navi.codingchallenge.models.LoanApplicationRequest;

public interface LoanProcessor {
    void processLoanRequest(LoanApplicationRequest request) throws InvalidInputException, NotImplementedException;
}
