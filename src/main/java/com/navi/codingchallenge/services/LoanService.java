package com.navi.codingchallenge.services;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.services.interfaces.LoanServiceInterface;
import com.navi.codingchallenge.services.repository.LoanRepository;

public class LoanService implements LoanServiceInterface  {
    private final LoanRepository loanRepository;

    public LoanService() {
        this.loanRepository = LoanRepository.geLoanRepositoryInstance();
    }


    public Loan getLoanForBorrowerAndBank(String bankName, String borrowerName, boolean throwExceptionIfNotFound) throws InvalidInputException {
        Loan loan = loanRepository.getLoanForBorrowerAndBank(bankName, borrowerName);
        if (throwExceptionIfNotFound && loan == null) {
            throw new InvalidInputException("Loan for bankName: " + bankName + " borrowerName:" + borrowerName + " doesn't exist");
        }

        return loan;
    }

    @Override
    public Loan save(Loan loan) {
        loanRepository.save(loan);
        return loan;
    }



}
