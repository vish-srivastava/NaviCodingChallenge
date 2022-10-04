package com.navi.codingchallenge.components;

import com.navi.codingchallenge.models.InputType;
import com.navi.codingchallenge.models.InvalidInputException;
import com.navi.codingchallenge.models.Request;
import lombok.Getter;

import java.util.*;


public class InputCommandHandler {

    private final LedgerManager ledgerManager;

    private final List<Request> requests;

    public LinkedList<Request> requestSequence;

    private final Map<Request, String> output;

    private static final Integer NEGATIVE_ONE = -1;
    private static final Integer POSITIVE_ONE = 1;

    private static final Integer ZERO = 0;
    private static final String DELIMITER = " ";

    public InputCommandHandler() {
        ledgerManager = new LedgerManager();
        requestSequence = new LinkedList<>();
        requests = new ArrayList<>();
        output = new LinkedHashMap<>();
    }

    public void handleInput(Request request) {
        requestSequence.add(request);
        if (request.getInputType().equals(InputType.LOAN)) {
            output.put(request, processInputType(request.getInputParams(), request.getInputType()));
        } else {
            requests.add(request);
        }
    }

    public Request parseRequest(String input) throws Exception {
        String[] inputParams = input.split(DELIMITER);
        InputType inputType = InputType.valueOf(inputParams[ZERO]);
        String emiNumber = null;
        if (inputType != InputType.LOAN) {
            emiNumber = inputParams[inputParams.length + NEGATIVE_ONE];
        }
        return new Request(inputType, emiNumber, inputParams);
    }

    private String processInputType(String[] input, InputType inputType) {
        String output = null;
        int index = 0;
        switch (inputType) {
            case LOAN: ledgerManager.processLoan(input[++index], input[++index], Double.parseDouble(input[++index]), Integer.parseInt(input[++index]), Double.parseDouble(input[++index])); break;
            case PAYMENT: ledgerManager.processLumpSumPayment(input[++index], input[++index], Double.parseDouble(input[++index]), Integer.parseInt(input[++index]));break;
            case BALANCE:output = ledgerManager.getBalance(input[++index], input[++index], Integer.parseInt(input[++index]));break;
        }
        return output;

    }

    public Map<Request, String> processRequests() {
        sortRequestOnMonthBasis();
        for (Request request : requests) {
            output.put(request, processInputType(request.getInputParams(), request.getInputType()));
        }
        return output;
    }

    private void sortRequestOnMonthBasis() {
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

    public List<Request> getSequnceOfRequests(){
        return this.requestSequence;
    }

}



