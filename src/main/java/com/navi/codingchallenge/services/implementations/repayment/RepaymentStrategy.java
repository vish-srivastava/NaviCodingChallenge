package com.navi.codingchallenge.services.implementations.repayment;

import com.navi.codingchallenge.models.Loan;

public interface RepaymentStrategy {
    void acceptPayment(Loan loan, Double repaymentAmount);
}
