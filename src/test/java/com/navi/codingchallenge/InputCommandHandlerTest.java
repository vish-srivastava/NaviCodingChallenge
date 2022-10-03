package com.navi.codingchallenge;

import com.navi.codingchallenge.components.InputCommandHandler;
import com.navi.codingchallenge.models.Request;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class InputCommandHandlerTest {

    private static InputCommandHandler inputCommandHandler;

    @Test
    public void testHandleInput() {
        // dummy test
        Assert.assertTrue(true);
    }

    @Test
    public void testParseRequest() {
        Assert.assertTrue(true);
    }

    @Test
    public void testProcessRequests() throws Exception {
        String[] testData = getTestData1();
        Queue<String> outputData = getOutputData1();
        inputCommandHandler = new InputCommandHandler();
        runTestData(testData, outputData); // test case 1
        inputCommandHandler = new InputCommandHandler();
        runTestData(getTestData2(), getOutputData2()); // test case 2
    }

    private void runTestData(String[] testData, Queue<String> outputData) throws Exception {
        for (String data : testData) {
            Request request = inputCommandHandler.parseRequest(data);
            inputCommandHandler.handleInput(request);
        }
        Map<Request, String> output = inputCommandHandler.processRequests();
        List<Request> outputSequnce = inputCommandHandler.getRequestSequence();

        for (Request sequnce : outputSequnce) {
            String op = output.get(sequnce);
            if (op != null && op.length() > 0) {
                Assert.assertEquals(op.trim(), Objects.requireNonNull(outputData.poll()).trim());
            }
        }
    }


    @Test
    public void testPrintOutput() {
        Assert.assertTrue(true);
    }

    @Test
    public void testGetRequestSequence() {
        Assert.assertTrue(true);
    }


    public String[] getTestData1() {
        return new String[]{"LOAN IDIDI Dale 5000 1 6",
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

    public String[] getTestData2() {
        return new String[]{
                "LOAN IDIDI Dale 10000 5 4",
                "LOAN MBI Harry 2000 2 2",
                "BALANCE IDIDI Dale 5",
                "BALANCE IDIDI Dale 40",
                "BALANCE MBI Harry 12",
                "BALANCE MBI Harry 0"
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
        output.offer("IDIDI Dale 1000 55");
        output.offer("IDIDI Dale 8000 20");
        output.offer("MBI Harry 1044 12");
        output.offer("MBI Harry 0 24");
        return output;

    }

}