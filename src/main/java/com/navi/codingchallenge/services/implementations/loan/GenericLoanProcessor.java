package com.navi.codingchallenge.services.implementations.loan;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.models.LoanApplicationRequest;
import com.navi.codingchallenge.models.LoanType;
import com.navi.codingchallenge.services.LoanService;
import com.navi.codingchallenge.services.interfaces.LoanProcessor;
import com.navi.codingchallenge.utils.ModelMapper;
import lombok.Getter;

import static com.navi.codingchallenge.models.Constants.MONTHS_IN_YEAR;
import static com.navi.codingchallenge.models.Constants.ZERO;


public class GenericLoanProcessor implements LoanProcessor {

    @Getter
    private static final LoanType loanType = LoanType.GENERIC;
    private final LoanService loanService;

    public GenericLoanProcessor() {
        this.loanService = new LoanService();

    }

    @Override
    public void processLoanRequest(LoanApplicationRequest request) throws InvalidInputException {
        if (loanService.getLoanForBorrowerAndBank(request.getBankName(), request.getBorrowerName()) != null) {
            throw new InvalidInputException("Loan for Borrower and Bank already Exists");
        }
        Loan loan = ModelMapper.mapLoanApplicationRequestToLoan(request);
        processLoan(loan);
        loanService.save(loan);
    }

    private void processLoan(Loan loan) {
        Double interest = loan.getPrinciple() * (loan.getRateOfInterest()) * loan.getYears();
        Double amount = loan.getPrinciple() + interest;
        loan.setTotalRepayableAmount(amount);

        Double monthlyEMIAmount = Math.ceil(loan.getTotalRepayableAmount() / (MONTHS_IN_YEAR * loan.getYears()));

        loan.setMonthlyEMIAmount(monthlyEMIAmount);
        loan.setLumpSumPaid(Double.valueOf(ZERO));
    }


}