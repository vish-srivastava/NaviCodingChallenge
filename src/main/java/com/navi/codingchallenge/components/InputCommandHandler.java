package com.navi.codingchallenge.components;

import com.navi.codingchallenge.models.InputType;
import com.navi.codingchallenge.models.Request;
import lombok.Getter;

import java.util.*;


public class InputCommandHandler {

    private final LedgerManager ledgerManager;

    private final List<Request> requests;

    @Getter
    public LinkedList<Request> requestSequence;

    private final Map<Request, String> output;

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
        String[] inputParams = validateInputAndGetParams(input);
        InputType inputType = InputType.valueOf(inputParams[0]);
        String emiNumber = null;
        if (inputType != InputType.LOAN) {
            emiNumber = inputParams[inputParams.length - 1];
        }
        return new Request(inputType, emiNumber, inputParams);
    }

    private String[] validateInputAndGetParams(String input) throws Exception {
        if (input == null || input.length() == 0) {
            throw new Exception("Invalid Input : Can't be empty");
        }

        String[] inputParams = input.split(" ");

        if (input.contains("LOAN") && inputParams.length != 6) {
            throw new Exception("Invalid Input");
        }

        if (input.contains("BALANCE") && inputParams.length != 4) {
            throw new Exception("Invalid Input");
        }

        if (input.contains("PAYMENT") && inputParams.length != 5) {
            throw new Exception("Invalid Input");
        }

        return inputParams;
    }

    private String processInputType(String[] input, InputType inputType) {
        String output = null;
        switch (inputType) {

            case LOAN:
                ledgerManager.processLoan(input[1], input[2], Double.parseDouble(input[3]), Integer.parseInt(input[4]), Double.parseDouble(input[5]));
                break;

            case PAYMENT:
                ledgerManager.processPayment(input[1], input[2], Double.parseDouble(input[3]), Integer.parseInt(input[4]));
                break;
            case BALANCE:
                output = ledgerManager.getBalance(input[1], input[2], Integer.parseInt(input[3]));
                break;
        }

        return output;

    }

    public Map<Request, String> processRequests() {
        requests.sort((o1, o2) -> {
            Integer emi1 = Integer.parseInt(o1.getEmiNumber());
            Integer emi2 = Integer.parseInt(o2.getEmiNumber());
            if (emi1.equals(emi2)) {
                if (o1.getInputType() == InputType.PAYMENT) {
                    return -1;
                } else return 1;
            }
            return emi1 - emi2;
        });

        for (Request request : requests) {
            output.put(request, processInputType(request.getInputParams(), request.getInputType()));
        }
        return output;
    }

    public void printOutput() {
        for (Request sequence : requestSequence) {
            String op = output.get(sequence);
            if (op != null && op.length() > 0) {
                System.out.println(op);
            }
        }
    }

}



