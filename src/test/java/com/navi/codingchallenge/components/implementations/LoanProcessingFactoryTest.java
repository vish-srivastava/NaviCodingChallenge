package com.navi.codingchallenge.components.implementations;

import com.navi.codingchallenge.components.implementations.loanprocessors.CarLoanProcessor;
import com.navi.codingchallenge.components.implementations.loanprocessors.GenericLoanProcessor;
import com.navi.codingchallenge.components.interfaces.LoanProcessor;
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