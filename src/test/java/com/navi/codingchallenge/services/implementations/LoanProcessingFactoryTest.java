package com.navi.codingchallenge.services.implementations;

import com.navi.codingchallenge.services.implementations.loan.CarLoanProcessor;
import com.navi.codingchallenge.services.implementations.loan.GenericLoanProcessor;
import com.navi.codingchallenge.services.implementations.loan.LoanProcessingFactory;
import com.navi.codingchallenge.services.interfaces.LoanProcessor;
import com.navi.codingchallenge.models.LoanType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoanProcessingFactoryTest {


    @Test
    void getLoanProcessorByLoanType() {
        LoanType loanType = LoanType.GENERIC;
        LoanProcessor processor = LoanProcessingFactory.getLoanProcessorByLoanType(loanType);
        Assertions.assertTrue(processor instanceof GenericLoanProcessor);
    }

    @Test
    void getLoanProcessorByLoanTypeCar() {
        LoanType loanType = LoanType.CAR;
        LoanProcessor processor = LoanProcessingFactory.getLoanProcessorByLoanType(loanType);
        Assertions.assertTrue(processor instanceof CarLoanProcessor);
    }
}