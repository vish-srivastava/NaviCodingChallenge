package com.navi.codingchallenge.components;

import com.navi.codingchallenge.components.implementations.DefaultRepaymentService;
import com.navi.codingchallenge.components.implementations.LoanProcessingFactory;
import com.navi.codingchallenge.components.interfaces.LoanProcessor;
import com.navi.codingchallenge.components.interfaces.RepaymentService;
import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.exceptions.NotImplementedException;
import com.navi.codingchallenge.models.LoanApplicationRequest;
import com.navi.codingchallenge.models.LumpSumPaymentRequest;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LedgerManager {
    public void processLoan(LoanApplicationRequest loanApplicationRequest) throws InvalidInputException, NotImplementedException {
        LoanProcessor loanProcessor = LoanProcessingFactory.getLoanProcessorByLoanType(loanApplicationRequest.getLoanType());
        loanProcessor.processLoanRequest(loanApplicationRequest);
    }

    public void processPayment(LumpSumPaymentRequest paymentRequest) throws InvalidInputException {
        RepaymentService repaymentService = new DefaultRepaymentService();
        repaymentService.processLumpSumPayment(paymentRequest);
    }

    public String getBalance(String bankName, String borrower, Integer emiNumber) throws InvalidInputException {
        RepaymentService repaymentService = new DefaultRepaymentService();
        return repaymentService.getOutstandingBalance(bankName, borrower, emiNumber);
    }

}
