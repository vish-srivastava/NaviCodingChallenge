package com.navi.codingchallenge;

import com.navi.codingchallenge.services.InputCommandHandler;
import com.navi.codingchallenge.models.Request;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws Exception {
        int first = 0;
        String filePath = args[first];
        File file = new File(filePath);
        parseAndProcessInput(file);

    }

    public static void parseAndProcessInput(File file) {
        InputCommandHandler inputCommandHandler = new InputCommandHandler();
        Scanner scanner = null;
        try (Scanner inputScanner = new Scanner(file);) {
            scanner = inputScanner;
            while (inputScanner.hasNextLine()) {
                String inputLine = inputScanner.nextLine();
                Request request = inputCommandHandler.parseRequest(inputLine);
                inputCommandHandler.handleInput(request);
            }
            inputCommandHandler.processRemainingRequestsInOrder();
            inputCommandHandler.printOutput();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }


}
