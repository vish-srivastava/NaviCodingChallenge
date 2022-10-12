package com.navi.codingchallenge.services.implementations.repayment;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.exceptions.NotImplementedException;
import com.navi.codingchallenge.models.BalanceRequest;
import com.navi.codingchallenge.models.BalanceResponse;
import com.navi.codingchallenge.models.Loan;
import com.navi.codingchallenge.models.LumpSumPaymentRequest;
import com.navi.codingchallenge.services.LoanService;
import com.navi.codingchallenge.services.interfaces.RepaymentService;

import static com.navi.codingchallenge.models.Constants.MONTHS_IN_YEAR;
import static com.navi.codingchallenge.models.Constants.ZERO;


public class SimpleInterestRepaymentService implements RepaymentService {

    private final LoanService loanService;

    public SimpleInterestRepaymentService() {
        this.loanService = new LoanService();
    }

    /**
     * RepaymentStrategy ->  Default  : Accept lump sum and monthly EMI as well
     *
     * @param lumpSumPaymentRequest
     * @throws InvalidInputException
     * @throws NotImplementedException
     */
    @Override
    public void processLumpSumPayment(LumpSumPaymentRequest lumpSumPaymentRequest) throws InvalidInputException, NotImplementedException {

        Loan loan = loanService.getLoanForBorrowerAndBank(lumpSumPaymentRequest.getBankName(), lumpSumPaymentRequest.getBorrowerName(), true);
        RepaymentStrategy repaymentStrategy = RepaymentStrategyFactory.getRepaymentStrategyForLoan(loan, loanService);
        repaymentStrategy.acceptLumpSumPayment(loan, lumpSumPaymentRequest.getLumpSum());
    }

    /**
     * Assumption : Below implementation is for Simple Interest calculation per year, with monthly installments
     *
     * @param loan
     */
    @Override
    public void processInterest(Loan loan) {
        Double interest = loan.getPrinciple() * (loan.getRateOfInterest()) * loan.getYears();
        Double amount = loan.getPrinciple() + interest;
        loan.setTotalRepayableAmount(amount);

        Double monthlyEMIAmount = Math.ceil(loan.getTotalRepayableAmount() / (MONTHS_IN_YEAR * loan.getYears()));

        loan.setMonthlyEMIAmount(monthlyEMIAmount);
        loan.setLumpSumPaid(Double.valueOf(ZERO));
    }

    @Override
    public BalanceResponse getOutstandingBalance(BalanceRequest balanceRequest) throws InvalidInputException {
        Loan loan = loanService.getLoanForBorrowerAndBank(balanceRequest.getBankName(), balanceRequest.getBorrower(), true);
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
