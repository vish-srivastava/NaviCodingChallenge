package com.navi.codingchallenge.services.implementations.loan;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.models.LoanApplicationRequest;
import com.navi.codingchallenge.models.LoanType;
import com.navi.codingchallenge.services.LoanService;
import com.navi.codingchallenge.services.interfaces.LoanProcessor;
import com.navi.codingchallenge.utils.ModelMapper;
import lombok.Getter;


public class GenericLoanProcessor implements LoanProcessor {

    @Getter
    private static final LoanType loanType = LoanType.GENERIC;
    private final LoanService loanService;

    public GenericLoanProcessor() {
        this.loanService = new LoanService();

    }

    @Override
    public Loan processLoanRequest(LoanApplicationRequest request) throws InvalidInputException {
        Loan existingLoan = loanService.getLoanForBorrowerAndBank(request.getBankName(), request.getBorrowerName(), false);
        if (existingLoan != null) {
            throw new InvalidInputException("Loan for this request already exists");
        }
        return ModelMapper.mapLoanApplicationRequestToLoan(request);
    }




}
