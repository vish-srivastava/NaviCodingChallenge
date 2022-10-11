package com.navi.codingchallenge.services;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.models.BalanceRequest;
import com.navi.codingchallenge.models.BalanceResponse;
import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.services.repository.LoanRepository;

import static com.navi.codingchallenge.models.Constants.ZERO;

public class LoanService {
    private final LoanRepository loanRepository;

    public LoanService() {
        this.loanRepository = LoanRepository.geLoanRepositoryInstance();
    }

    public Loan getLoanForBorrowerAndBank(String bankName, String borrowerName) {
        return loanRepository.getLoanForBorrowerAndBank(bankName, borrowerName);
    }

    public Loan save(Loan loan) {
        loanRepository.save(loan);
        return loan;
    }

    public BalanceResponse getOutstandingBalance(BalanceRequest balanceRequest) throws InvalidInputException {
        Loan loan = getLoanForBorrowerAndBank(balanceRequest.getBankName(), balanceRequest.getBorrower());
        if (loan == null) {
            throw new InvalidInputException("No loan found for bank : " + balanceRequest.getBankName() + " & borrowerName : " + balanceRequest.getBorrower());
        }

        int amountRepaidSoFar = getAmountRepaid(loan, balanceRequest.getEmiNumber());
        return BalanceResponse.builder()
                .bankName(loan.getBankName())
                .borrowerName(loan.getBorrowerName())
                .amountRepaid(amountRepaidSoFar)
                .remainingEmis(getRemainingEMIs(loan, balanceRequest.getEmiNumber()))
                .outstandingAmount(Math.min(ZERO, (int) (loan.getTotalRepayableAmount() - amountRepaidSoFar)))
                .build();
    }


    private Integer getRemainingEMIs(Loan loan, Integer emisPaid) {
        Double outstandingAmount = loan.getTotalRepayableAmount() - (loan.getLumpSumPaid() + loan.getMonthlyEMIAmount() * emisPaid);
        int remainingEmis = (int) Math.ceil(outstandingAmount / loan.getMonthlyEMIAmount());
        return Math.max(remainingEmis, 0);
    }

    private Integer getAmountRepaid(Loan loan, Integer emisPaid) {
        return (int) Math.min((Math.ceil(loan.getLumpSumPaid() + loan.getMonthlyEMIAmount() * emisPaid)), loan.getTotalRepayableAmount());

    }

}
