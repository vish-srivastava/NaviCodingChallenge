package com.navi.codingchallenge.services.interfaces;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.exceptions.NotImplementedException;
import com.navi.codingchallenge.models.LumpSumPaymentRequest;

public interface RepaymentService {

    /**
     * Process payments based on loan type and repayment strategy
     * @param lumpSumPaymentRequest
     * @throws InvalidInputException
     * @throws NotImplementedException
     */
    void processLumpSumPayment(LumpSumPaymentRequest lumpSumPaymentRequest) throws InvalidInputException, NotImplementedException;

}
