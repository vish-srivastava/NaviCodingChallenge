package com.navi.codingchallenge.services.implementations.repayment;

import com.navi.codingchallenge.models.Loan;

public interface RepaymentStrategy {
    void acceptLumpSumPayment(Loan loan, Double repaymentAmount);
}
