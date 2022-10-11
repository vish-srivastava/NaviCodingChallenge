package com.navi.codingchallenge.components;

import com.navi.codingchallenge.exceptions.InvalidInputException;
import com.navi.codingchallenge.exceptions.NotImplementedException;
import com.navi.codingchallenge.models.*;

import java.util.*;

import static com.navi.codingchallenge.models.Constants.*;


public class InputCommandHandler {

    private final LedgerManager loanManager;

    private final List<Request> requests;

    public final List<Request> requestSequence;

    private final Map<Request, String> output;

    public InputCommandHandler() {
        loanManager = new LedgerManager();
        requestSequence = new LinkedList<>();
        requests = new ArrayList<>();
        output = new LinkedHashMap<>();
    }

    private String[] validateInputAndGetParams(String[] inputParams) throws InvalidInputException {
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

    public void handleInput(Request request) throws InvalidInputException, NotImplementedException {
        requestSequence.add(request);
        if (request.getInputType().equals(InputType.LOAN)) {
            output.put(request, processInputType(request.getInputParams(), request.getInputType()));
        } else {
            requests.add(request);
        }
    }

    public Request parseRequest(String input) throws InvalidInputException {
        String[] inputParams = input.split(DELIMITER);
        validateInputAndGetParams(inputParams);
        InputType inputType = InputType.valueOf(inputParams[ZERO]);
        String emiNumber = null;
        if (inputType != InputType.LOAN) {
            emiNumber = inputParams[inputParams.length + NEGATIVE_ONE];
        }
        return new Request(inputType, emiNumber, inputParams);
    }

    private String processInputType(String[] input, InputType inputType) throws InvalidInputException, NotImplementedException {
        String result = null;
        int index = ZERO;

        switch (inputType) {

            case LOAN:
                LoanApplicationRequest request = LoanApplicationRequest.builder()
                        .bankName(input[++index])
                        .borrowerName(input[++index])
                        .principle(Double.parseDouble(input[++index]))
                        .numberOfYears(Integer.parseInt(input[++index]))
                        .rateOfInterest(Double.parseDouble(input[++index]))
                        .loanType(LoanType.GENERIC)
                        .build();
                loanManager.processLoan(request);
                break;


            case PAYMENT:
                LumpSumPaymentRequest paymentRequest = LumpSumPaymentRequest.builder()
                        .bankName(input[++index])
                        .borrowerName(input[++index])
                        .lumpSum(Double.parseDouble(input[++index]))
                        .emisPaid(Integer.parseInt(input[++index]))
                        .build();
                loanManager.processPayment(paymentRequest);
                break;


            case BALANCE:
                result = loanManager.getBalance(input[++index], input[++index], Integer.parseInt(input[++index]));
                break;

            default:
                throw new InvalidInputException("Input is Invalid/Not Supported");

        }
        return result;
    }

    public Map<Request, String> processRemainingRequestsInOrder() throws InvalidInputException, NotImplementedException {
        sanitiseRequests();
        for (Request request : requests) {
            output.put(request, processInputType(request.getInputParams(), request.getInputType()));
        }
        return output;
    }

    private void sanitiseRequests() {
        requests.sort((o1, o2) -> {
            Integer emi1 = Integer.parseInt(o1.getEmiNumber());
            Integer emi2 = Integer.parseInt(o2.getEmiNumber());
            if (emi1.equals(emi2)) {
                if (o1.getInputType() == InputType.PAYMENT) {
                    return NEGATIVE_ONE;
                } else return POSITIVE_ONE;
            }
            return emi1 - emi2;
        });
    }

    public void printOutput() {
        for (Request sequence : requestSequence) {
            String op = output.get(sequence);
            if ((op != null) && (op.length() > ZERO)) {
                System.out.println(op);
            }
        }
    }

    public List<Request> getSequnceOfRequests() {
        return this.requestSequence;
    }

}



