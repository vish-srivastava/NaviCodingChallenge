package com.navi.codingchallenge.services.implementations.repayment;

import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.services.LoanService;

public class DefaultRepaymentStrategy implements RepaymentStrategy {

    private LoanService loanService;

    public DefaultRepaymentStrategy(LoanService loanService) {
        this.loanService = loanService;
    }

    @Override
    public void acceptLumpSumPayment(Loan loan, Double repaymentAmount) {
        Double lumpSumPaidSoFar = loan.getLumpSumPaid();
        if (lumpSumPaidSoFar > loan.getTotalRepayableAmount()) {
            lumpSumPaidSoFar = loan.getTotalRepayableAmount();
        }
        loan.setLumpSumPaid(lumpSumPaidSoFar + repaymentAmount);
        loanService.save(loan);
    }
}
