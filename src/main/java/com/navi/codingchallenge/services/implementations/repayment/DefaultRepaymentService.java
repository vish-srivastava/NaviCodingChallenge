package com.navi.codingchallenge.services.implementations.repayment;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.exceptions.NotImplementedException;
import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.models.LumpSumPaymentRequest;
import com.navi.codingchallenge.services.LoanService;
import com.navi.codingchallenge.services.interfaces.RepaymentService;

public class DefaultRepaymentService implements RepaymentService {

    private final LoanService loanService;

    public DefaultRepaymentService() {
        this.loanService = new LoanService();
    }

    @Override
    public void processLumpSumPayment(LumpSumPaymentRequest lumpSumPaymentRequest) throws InvalidInputException, NotImplementedException {

        Loan loan = loanService.getLoanForBorrowerAndBank(lumpSumPaymentRequest.getBankName(), lumpSumPaymentRequest.getBorrowerName());

        if (loan == null)
            throw new InvalidInputException("No loan found for bank : " + lumpSumPaymentRequest.getBankName() + " & borrowerName : " + lumpSumPaymentRequest.getBorrowerName());

        RepaymentStrategy repaymentStrategy = RepaymentStrategyFactory.getRepaymentStrategyForLoan(loan, loanService);

        repaymentStrategy.acceptLumpSumPayment(loan, lumpSumPaymentRequest.getLumpSum());
    }


}
