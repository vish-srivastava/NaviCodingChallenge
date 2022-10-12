package com.navi.codingchallenge.utils;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.models.BalanceRequest;
import com.navi.codingchallenge.models.InputType;
import com.navi.codingchallenge.models.LoanApplicationRequest;
import com.navi.codingchallenge.models.PaymentRequest;

import static com.navi.codingchallenge.models.Constants.*;

public class ValidationUtils {

    /**
     * Making constructor private to prevent public call
     */
    private ValidationUtils() {
        // do nothing
    }

    public static String[] validateInputAndGetParams(String[] inputParams) throws InvalidInputException {
        if (inputParams == null || inputParams.length == ZERO) {
            throw new InvalidInputException("Invalid Input : Can't be empty");
        }

        InputType inputType = null;
        try {
            inputType = InputType.valueOf(inputParams[ZERO]);
        } catch (Exception e) {
            throw new InvalidInputException("Invalid Input Format");
        }

        if (inputType == null) {
            throw new InvalidInputException("Invalid Input Format ");
        }

        if (inputType == InputType.LOAN && inputParams.length != SIX) {
            throw new InvalidInputException(INVALID_INPUT_ERROR);
        }

        if (inputType == InputType.BALANCE && inputParams.length != FOUR) {
            throw new InvalidInputException(INVALID_INPUT_ERROR);
        }

        if (inputType == InputType.PAYMENT && inputParams.length != FIVE) {
            throw new InvalidInputException(INVALID_INPUT_ERROR);
        }

        return inputParams;
    }

    public static void validateLoanApplciation(LoanApplicationRequest applicationRequest) throws InvalidInputException {
        if (applicationRequest.getNumberOfYears() > MAX_LOAN_TERM) {
            throw new InvalidInputException("Invalid Loan term: max number of years allowed :" + MAX_LOAN_TERM);
        }
        if ((applicationRequest.getRateOfInterest() / 100.00) > MAX_LOAN_INTEREST) {
            throw new InvalidInputException("Invalid Loan interest rate: max number of years allowed :" + MAX_LOAN_INTEREST);
        }
    }

    public static void validatePaymentRequest(PaymentRequest paymentRequest) throws InvalidInputException {
        if (paymentRequest.getBorrowerName() == null || paymentRequest.getBorrowerName().length() == 0) {
            throw new InvalidInputException("Borrower name can't be empty");
        }

        if (paymentRequest.getBankName() == null || paymentRequest.getBankName().length() == 0) {
            throw new InvalidInputException("Bank name can't be empty");
        }

        if (paymentRequest.getLumpSum() < 0) {
            throw new InvalidInputException("Lumpsum payment amount can't be negative");
        }

        if (paymentRequest.getEmisPaid() < 0) {
            throw new InvalidInputException("EMIs paid can't be negative");
        }

    }

    public static void validateBalanceRequest(BalanceRequest balanceRequest) throws InvalidInputException {
        if (balanceRequest.getBorrower() == null || balanceRequest.getBorrower().length() == 0) {
            throw new InvalidInputException("Borrower name can't be empty");
        }

        if (balanceRequest.getBankName() == null || balanceRequest.getBankName().length() == 0) {
            throw new InvalidInputException("Bank name can't be empty");
        }

        if (balanceRequest.getEmiNumber() < 0) {
            throw new InvalidInputException("EMIs paid can't be negative");
        }
    }


}
