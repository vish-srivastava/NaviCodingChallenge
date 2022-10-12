package com.navi.codingchallenge.services.implementations.repayment;

import com.navi.codingchallenge.exceptions.NotImplementedException;
import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.services.LoanService;

public class RepaymentStrategyFactory {

    private RepaymentStrategyFactory() {
        // prevent public contstructor call
    }

    public static RepaymentStrategy getRepaymentStrategyForLoan(Loan loan, LoanService loanService) throws NotImplementedException {
        switch (loan.getInterestType()) {
            case FIXED_COMPOUND_INTEREST:
            case FLOATING_COMPOUND_INTEREST:
            case FLOATING_SIMPLE_INTEREST:
                throw new NotImplementedException();
            default:
                return new LumpSumRepaymentStrategy(loanService);
        }
    }
}
