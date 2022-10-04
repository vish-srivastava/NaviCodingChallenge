package com.navi.codingchallenge.components;

import com.navi.codingchallenge.models.Loan;

import java.util.HashMap;
import java.util.Map;


public class LedgerManager {

    private final Map<String, Loan> ledger;
    private static final String delimiter = " ";

    private static final Double ONE_HUNDRED = 100.00;

    private static final Double ZERO = 0.00;

    public LedgerManager() {
        ledger = new HashMap<>();
    }

    /**
     * Format - LOAN BANK_NAME BORROWER_NAME PRINCIPAL NO_OF_YEARS RATE_OF_INTEREST
     * Example- LOAN IDIDI Dale 10000 5 4 means a loan amount of 10000 is paid to Dale by IDIDI for a tenure of 5 years at 4% rate of interest.
     */
    public void processLoan(String bankName, String borrowerName, Double principle, Integer numberOfYears, Double rateOfInterest) {
        Loan loanRequest = Loan.builder().bankName(bankName).borrowerName(borrowerName).principle(principle).years(numberOfYears).rateOfInterest((rateOfInterest / ONE_HUNDRED)).build();
        loanRequest.processLoan();
        ledger.put(getLoanID(bankName, borrowerName), loanRequest);
    }


    /**
     * Format - PAYMENT BANK_NAME BORROWER_NAME LUMP_SUM_AMOUNT EMI_NO
     * Example - PAYMENT MBI Dale 1000 5 means a lump sum payment of 1000 was done by Dale to MBI after 5 EMI payments.
     */
    public void processLumpSumPayment(String bankName, String borrowerName, Double lumpSum, Integer emisPaid) {
        Loan loan = getLoanForBorrowerAndBank(bankName, borrowerName);
        if (loan == null) {
            return;
        }
        loan.acceptLumpSumPayment(lumpSum);
    }

    public String getBalance(String bankName, String borrowerName, Integer emi) {
        Loan loan = getLoanForBorrowerAndBank(bankName, borrowerName);
        loan.acceptLumpSumPayment(ZERO);
        return loan.getBorrowersBankName() + delimiter +
                loan.getBorrowersName() + delimiter +
                loan.getAmountRepaid(emi) + delimiter +
                loan.getRemainingEMIs(emi);
    }

    private String getLoanID(String bankName, String borrowerName) {
        return bankName + ":" + borrowerName;
    }

    private Loan getLoanForBorrowerAndBank(String bankName, String borrowerName) {
        String loanId = getLoanID(bankName, borrowerName);
        return ledger.get(loanId);
    }

}
