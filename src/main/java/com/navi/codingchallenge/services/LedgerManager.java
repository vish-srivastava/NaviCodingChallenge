package com.navi.codingchallenge.services;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.exceptions.NotImplementedException;
import com.navi.codingchallenge.models.*;
import com.navi.codingchallenge.services.implementations.loan.LoanProcessingFactory;
import com.navi.codingchallenge.services.implementations.repayment.SimpleInterestRepaymentService;
import com.navi.codingchallenge.services.interfaces.LoanProcessor;
import com.navi.codingchallenge.services.interfaces.RepaymentService;


public class LedgerManager {
    private final LoanService loanService;

    public LedgerManager() {
        this.loanService = new LoanService();
    }

    /**
     * @param loanApplicationRequest
     * @throws InvalidInputException   -> thrown for invalid loan creation requests
     * @throws NotImplementedException -> thrown when a repayment stratgy is not implemented yet
     */
    public void processLoan(LoanApplicationRequest loanApplicationRequest) throws InvalidInputException, NotImplementedException {
        // process loan
        LoanProcessor loanProcessor = LoanProcessingFactory.getLoanProcessorByLoanType(loanApplicationRequest.getLoanType());
        Loan processedLoan = loanProcessor.processLoanRequest(loanApplicationRequest);
        // process interest calculation for repayment
        RepaymentService repaymentService = getRepaymentServiceForLoan(processedLoan.getInterestType());
        repaymentService.processInterest(processedLoan);

        loanService.save(processedLoan);
    }

    public void processPayment(PaymentRequest paymentRequest) throws InvalidInputException, NotImplementedException {
        Loan loan = loanService.getLoanForBorrowerAndBank(paymentRequest.getBankName(), paymentRequest.getBorrowerName(), true);
        RepaymentService repaymentService = getRepaymentServiceForLoan(loan.getInterestType());
        repaymentService.processPayment(paymentRequest);
    }

    public String getBalance(BalanceRequest balanceRequest) throws InvalidInputException, NotImplementedException {
        Loan loan = loanService.getLoanForBorrowerAndBank(balanceRequest.getBankName(), balanceRequest.getBorrower(), true);
        RepaymentService repaymentService = getRepaymentServiceForLoan(loan.getInterestType());
        BalanceResponse balanceResponse = repaymentService.getOutstandingBalance(balanceRequest);
        return balanceResponse.toString();
    }

    public RepaymentService getRepaymentServiceForLoan(InterestType interestType) throws NotImplementedException {
        switch (interestType) {
            case FIXED_COMPOUND_INTEREST:
            case FLOATING_SIMPLE_INTEREST:
            case FLOATING_COMPOUND_INTEREST:
                throw new NotImplementedException();
            case FIXED_SIMPLE_INTEREST:
            default:
                return new SimpleInterestRepaymentService();
        }
    }

}
