package com.navi.codingchallenge;

import com.navi.codingchallenge.models.EventType;

import java.time.Month;

public class InputCommandHandler {

    private MarketEventManager eventManager;

    public InputCommandHandler() {
        eventManager = new MarketEventManager();
        eventManager.registerEventListeners();
    }

    public void handleInput(String input) throws Exception {
        String[] inputParams = validateInputAndGetParams(input);
        EventType eventType = EventType.valueOf(inputParams[0]);
        eventManager.notifyListener(eventType, inputParams);
    }

    private String[] validateInputAndGetParams(String input) throws Exception {
        if (input == null || input.length() == 0) {
            throw new Exception("Invalid Input : Can't be empty");
        }

        String[] inputParams = input.split(" ");

        if (input.contains("ALLOCATE") && inputParams.length != 4) {
            throw new Exception("Invalid Input");
        } else if (input.contains("ALLOCATE") && eventManager.isPortfolioAllocated()) {
            throw new Exception("Invalid Input: Can't allocate portfolio twice");
        }

        if (input.contains("SIP") && inputParams.length != 4) {
            throw new Exception("Invalid Input");
        }

        if (input.contains("CHANGE") && inputParams.length != 5) {
            throw new Exception("Invalid Input");
        }

        if (input.contains("BALANCE") && !input.contains("REBALANCE") && inputParams.length != 2) {
            throw new Exception("Invalid Input");
        }

        if (input.contains("REBALANCE") && inputParams.length != 1) {
            throw new Exception("Invalid Input");
        }
        return inputParams;
    }

    private void validateMonth(String monthString) throws Exception {
        try {
            Month month = Month.valueOf(monthString);
        } catch (Exception e) {
            throw new Exception("Invalid value for month");
        }
    }


}



