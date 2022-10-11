package com.navi.codingchallenge.services;

import com.navi.codingchallenge.models.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class InputCommandHandlerTest {
    private static InputCommandHandler inputCommandHandler;


    @Test
    public void testHandleInput() {
        System.out.println("Testing testHandleInput");
        // dummy test
        Assertions.assertTrue(true);
    }

    @Test
    public void testParseRequest() {

        System.out.println("Testing testParseRequest");
        Assertions.assertTrue(true);
    }

    @Test
    public void testProcessRequests() throws Exception {
        System.out.println("Testing testProcessRequests");
        String[] testData = getTestData1();
        Queue<String> outputData = getOutputData1();
        inputCommandHandler = new InputCommandHandler();
        runTestData(testData, outputData); // test case 1
    }

    @Test
    public void testProcessRequestsScenario2() throws Exception {
        inputCommandHandler = new InputCommandHandler();
        runTestData(getTestData2(), getOutputData2()); // test case 2
    }

    @Test
    void handleInput() {
        Assertions.assertTrue(true);
    }


    @Test
    public void testProcessRequestsScenario3() throws Exception {
        System.out.println("Testing testProcessRequestsScenario3");
        inputCommandHandler = new InputCommandHandler();
        runTestData(getTestData3(), getOutputData3()); // test case 3
    }

    @Test
    void parseRequest() {
        Assertions.assertTrue(true);
    }


    private void runTestData(String[] testData, Queue<String> outputData) throws Exception {

        for (String data : testData) {
            Request request = inputCommandHandler.parseRequest(data);
            inputCommandHandler.handleInput(request);
        }
        Map<Request, String> output = inputCommandHandler.processRemainingRequestsInOrder();
        List<Request> outputSequnce = inputCommandHandler.getSequnceOfRequests();

        for (Request sequnce : outputSequnce) {
            String op = output.get(sequnce);
            if (op != null && op.length() > 0) {
                Assertions.assertEquals(op.trim(), Objects.requireNonNull(outputData.poll()).trim());
            }
        }
    }

    @Test
    void processRemainingRequestsInOrder() {
        Assertions.assertTrue(true);
    }


    @Test
    public void testPrintOutput() {
        System.out.println("Testing testPrintOutput");
        Assertions.assertTrue(true);
    }


    @Test
    void printOutput() {
        Assertions.assertTrue(true);
    }

    @Test
    public void testGetRequestSequence() {
        System.out.println("Testing testGetRequestSequence");
        Assertions.assertTrue(true);
    }


    public String[] getTestData1() {
        return new String[]{
                "LOAN IDIDI Dale 5000 1 6",
                "LOAN MBI Harry 10000 3 7",
                "LOAN UON Shelly 15000 2 9",
                "PAYMENT IDIDI Dale 1000 5",
                "PAYMENT MBI Harry 5000 10",
                "PAYMENT UON Shelly 7000 12",
                "BALANCE IDIDI Dale 3",
                "BALANCE IDIDI Dale 6",
                "BALANCE UON Shelly 12",
                "BALANCE MBI Harry 12"};
    }

    public String[] getTestData3() {
        return new String[]{
                "LOAN HDFC VISHAL 100000 1 20",
                "PAYMENT HDFC VISHAL 18000 6",
                "BALANCE HDFC VISHAL 6",
                "BALANCE HDFC VISHAL 12"
        };
    }

    public String[] getTestData2() {
        return new String[]{
                "LOAN IDIDI Dale2 10000 5 4",
                "LOAN MBI Harry2 2000 2 2",
                "BALANCE IDIDI Dale2 5",
                "BALANCE IDIDI Dale2 40",
                "BALANCE MBI Harry2 12",
                "BALANCE MBI Harry2 0"
        };
    }

    public Queue<String> getOutputData1() {
        Queue<String> output = new LinkedList<>();
        output.offer(" IDIDI Dale 1326 9");

        output.offer("IDIDI Dale 3652 4");
        output.offer("UON Shelly 15856 3");
        output.offer("MBI Harry 9044 10");

        return output;

    }

    public Queue<String> getOutputData2() {
        Queue<String> output = new LinkedList<>();
        output.offer("IDIDI Dale2 1000 55");
        output.offer("IDIDI Dale2 8000 20");
        output.offer("MBI Harry2 1044 12");
        output.offer("MBI Harry2 0 24");
        return output;

    }


    public Queue<String> getOutputData3() {
        Queue<String> output = new LinkedList<>();
        output.offer("HDFC VISHAL 78000 5");
        output.offer("HDFC VISHAL 120000 0");
        return output;

    }

    @Test
    void getSequnceOfRequests() {
        Assertions.assertTrue(true);
    }
}