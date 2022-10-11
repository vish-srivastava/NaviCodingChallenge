package com.navi.codingchallenge.components.interfaces;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.models.LumpSumPaymentRequest;

public interface RepaymentService {

    void processEmi(String bankName, String borrower, Integer emiNumber) throws InvalidInputException;

    void processLumpSumPayment(LumpSumPaymentRequest lumpSumPaymentRequest) throws InvalidInputException;

    String getOutstandingBalance(String bankName, String borrower, Integer emiNumber) throws InvalidInputException;

}
