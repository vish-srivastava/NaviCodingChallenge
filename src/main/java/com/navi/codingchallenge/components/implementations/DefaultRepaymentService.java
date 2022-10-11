package com.navi.codingchallenge.components.implementations;

import com.navi.codingchallenge.components.interfaces.RepaymentService;
import com.navi.codingchallenge.components.repository.LoanRepository;
import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.models.LumpSumPaymentRequest;

import static com.navi.codingchallenge.models.Constants.DELIMITER;
import static com.navi.codingchallenge.models.Constants.ZERO;

public class DefaultRepaymentService implements RepaymentService {

    private final LoanRepository loanRepository;

    public DefaultRepaymentService() {
        this.loanRepository = LoanRepository.geLoanRepositoryInstance();
    }

    @Override
    public void processEmi(String bankName, String borrower, Integer emiNumber) throws InvalidInputException {
        // TODO : Implement <Not a requirement>
    }

    @Override
    public void processLumpSumPayment(LumpSumPaymentRequest lumpSumPaymentRequest) throws InvalidInputException {
        Loan loan = loanRepository.getLoanForBorrowerAndBank(lumpSumPaymentRequest.getBankName(), lumpSumPaymentRequest.getBorrowerName());
        if (loan == null)
            throw new InvalidInputException("No loan found for bank : " + lumpSumPaymentRequest.getBankName() + " & borrowerName : " + lumpSumPaymentRequest.getBorrowerName());
        acceptLumpSumPayment(loan, lumpSumPaymentRequest.getLumpSum());
    }

    public void acceptLumpSumPayment(Loan loan, Double repaymentAmount) {
        Double lumpSumPaidSoFar = loan.getLumpSumPaid();
        if (lumpSumPaidSoFar > loan.getAmount()) {
            lumpSumPaidSoFar = loan.getAmount();
        }
        loan.setLumpSumPaid(lumpSumPaidSoFar + repaymentAmount);
        loanRepository.save(loan);
    }

    @Override
    public String getOutstandingBalance(String bankName, String borrower, Integer emiNumber) throws InvalidInputException {
        Loan loan = loanRepository.getLoanForBorrowerAndBank(bankName, borrower);
        if (loan == null) {
            throw new InvalidInputException("No loan found for bank : " + bankName + " & borrowerName : " + borrower);
        }
        acceptLumpSumPayment(loan, Double.valueOf(ZERO));

        return loan.getBankName() + DELIMITER +
                loan.getBorrowerName() + DELIMITER +
                getAmountRepaid(loan, emiNumber) + DELIMITER +
                getRemainingEMIs(loan, emiNumber);
    }

    public Integer getRemainingEMIs(Loan loan, Integer emisPaid) {
        Double outstandingAmount = loan.getAmount() - (loan.getLumpSumPaid() + loan.getMonthlyEMIAmount() * emisPaid);
        int remainingEmis = (int) Math.ceil(outstandingAmount / loan.getMonthlyEMIAmount());
        return Math.max(remainingEmis, 0);
    }

    public Integer getAmountRepaid(Loan loan, Integer emisPaid) {
        return (int) Math.min((Math.ceil(loan.getLumpSumPaid() + loan.getMonthlyEMIAmount() * emisPaid)), loan.getAmount());

    }

}
