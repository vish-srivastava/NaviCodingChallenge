package com.navi.codingchallenge.components.implementations.loanprocessors;

import com.navi.codingchallenge.utils.CommonUtils;
import com.navi.codingchallenge.components.interfaces.LoanProcessor;
import com.navi.codingchallenge.components.repository.LoanRepository;
import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.models.LoanApplicationRequest;
import com.navi.codingchallenge.models.LoanType;
import lombok.Getter;

import static com.navi.codingchallenge.models.Constants.MONTHS_IN_YEAR;
import static com.navi.codingchallenge.models.Constants.ZERO;


public class GenericLoanProcessor implements LoanProcessor {

    @Getter
    private static final LoanType loanType = LoanType.GENERIC;
    private final LoanRepository loanRepository;

    public GenericLoanProcessor() {
        this.loanRepository = LoanRepository.geLoanRepositoryInstance();

    }

    @Override
    public void processLoanRequest(LoanApplicationRequest request) throws InvalidInputException {
        if (loanRepository.getLoanForBorrowerAndBank(request.getBankName(), request.getBorrowerName()) != null) {
            throw new InvalidInputException("Loan for Borrower and Bank already Exists");
        }
        Loan loan = CommonUtils.mapLoanApplicationRequestToLoan(request);
        processLoan(loan);
        loanRepository.save(loan);
    }

    public void processLoan(Loan loan) {
        Double interest = loan.getPrinciple() * (loan.getRateOfInterest()) * loan.getYears();
        Double amount = loan.getPrinciple() + interest;
        loan.setAmount(amount);

        Double monthlyEMIAmount = Math.ceil(loan.getAmount() / (MONTHS_IN_YEAR * loan.getYears()));

        loan.setMonthlyEMIAmount(monthlyEMIAmount);
        loan.setLumpSumPaid(Double.valueOf(ZERO));
    }


}
