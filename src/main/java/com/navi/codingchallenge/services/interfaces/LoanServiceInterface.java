package com.navi.codingchallenge.services.interfaces;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.models.Loan;

public interface LoanServiceInterface {

    Loan getLoanForBorrowerAndBank(String bankName, String borrowerName, boolean throwExceptionIfNotFound) throws InvalidInputException;

    Loan save(Loan loan) ;


}
