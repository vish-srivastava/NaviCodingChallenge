package com.navi.codingchallenge.services;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.exceptions.NotImplementedException;
import com.navi.codingchallenge.models.BalanceRequest;
import com.navi.codingchallenge.models.BalanceResponse;
import com.navi.codingchallenge.models.LoanApplicationRequest;
import com.navi.codingchallenge.models.LumpSumPaymentRequest;
import com.navi.codingchallenge.services.implementations.loan.LoanProcessingFactory;
import com.navi.codingchallenge.services.implementations.repayment.DefaultRepaymentService;
import com.navi.codingchallenge.services.interfaces.LoanProcessor;
import com.navi.codingchallenge.services.interfaces.RepaymentService;


public class LedgerManager {

    private final LoanService loanService;

    public LedgerManager() {
        this.loanService = new LoanService();
    }

    public void processLoan(LoanApplicationRequest loanApplicationRequest) throws InvalidInputException, NotImplementedException {
        LoanProcessor loanProcessor = LoanProcessingFactory.getLoanProcessorByLoanType(loanApplicationRequest.getLoanType());
        loanProcessor.processLoanRequest(loanApplicationRequest);
    }


    public void processPayment(LumpSumPaymentRequest paymentRequest) throws InvalidInputException, NotImplementedException {
        RepaymentService repaymentService = new DefaultRepaymentService();
        repaymentService.processLumpSumPayment(paymentRequest);
    }

    public String getBalance(BalanceRequest balanceRequest) throws InvalidInputException {
        BalanceResponse balanceResponse= loanService.getOutstandingBalance(balanceRequest);
        return balanceResponse.toString();
    }

}
