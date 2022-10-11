package com.navi.codingchallenge.components.repository;

import com.navi.codingchallenge.models.Loan;

import java.util.HashMap;
import java.util.Map;


public final class LoanRepository {
    private static LoanRepository repository;

    //Hack to store data in memory : can be replaced by DB Query layer
    private static Map<String, Loan> loanMapping;

    // Single instance of data access repo
    public static LoanRepository geLoanRepositoryInstance() {

        // double check locking to prevent race condition
        LoanRepository repositoryInstance = repository;
        if (repositoryInstance != null) {
            return repositoryInstance;
        }

        // thread safety
        synchronized (LoanRepository.class) {
            // double check
            if (repository == null) {
                repository = new LoanRepository();
                loanMapping = new HashMap<>();
            }
            return repository;
        }

    }

    public void save(String bankName, String borrowerName, Loan loan) {
        loanMapping.put(getLoanID(bankName, borrowerName), loan);
    }

    public void save(Loan loan) {
        loanMapping.put(getLoanID(loan.getBankName(), loan.getBorrowerName()), loan);
    }

    private String getLoanID(String bankName, String borrowerName) {
        return bankName + ":" + borrowerName;
    }

    public Loan getLoanForBorrowerAndBank(String bankName, String borrowerName) {
        String loanId = getLoanID(bankName, borrowerName);
        return loanMapping.get(loanId);
    }

}
