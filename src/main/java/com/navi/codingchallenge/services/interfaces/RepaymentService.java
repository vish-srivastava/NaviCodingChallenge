package com.navi.codingchallenge.services.interfaces;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.exceptions.NotImplementedException;
import com.navi.codingchallenge.models.BalanceRequest;
import com.navi.codingchallenge.models.BalanceResponse;
import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.models.LumpSumPaymentRequest;

public interface RepaymentService {

    /**
     * Process payments based on loan type and repayment strategy
     * @param lumpSumPaymentRequest
     * @throws InvalidInputException
     * @throws NotImplementedException
     */
    void processLumpSumPayment(LumpSumPaymentRequest lumpSumPaymentRequest) throws InvalidInputException, NotImplementedException;

    void processInterest(Loan loan) ;

    BalanceResponse getOutstandingBalance(BalanceRequest balanceRequest) throws InvalidInputException;


}
