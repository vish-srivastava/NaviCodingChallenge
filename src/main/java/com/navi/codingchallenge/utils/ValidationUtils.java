package com.navi.codingchallenge.utils;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.models.InputType;
import com.navi.codingchallenge.models.LoanApplicationRequest;

import static com.navi.codingchallenge.models.Constants.*;

public class ValidationUtils {

    /**
     * Making constructor private to prevent public call
     */
    private ValidationUtils(){
        // do nothing
    }

    public static String[] validateInputAndGetParams(String[] inputParams) throws InvalidInputException {
        if (inputParams == null || inputParams.length == ZERO) {
            throw new InvalidInputException("Invalid Input : Can't be empty");
        }

        String inputType = inputParams[ZERO];

        if (inputType.equalsIgnoreCase(InputType.LOAN.name()) && inputParams.length != SIX) {
            throw new InvalidInputException(INVALID_INPUT_ERROR);
        }

        if (inputType.equalsIgnoreCase(InputType.BALANCE.name()) && inputParams.length != FOUR) {
            throw new InvalidInputException(INVALID_INPUT_ERROR);
        }

        if (inputType.equalsIgnoreCase(InputType.PAYMENT.name()) && inputParams.length != FIVE) {
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

}
