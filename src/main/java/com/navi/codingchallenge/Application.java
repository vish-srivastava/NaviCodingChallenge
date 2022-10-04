package com.navi.codingchallenge;

import com.navi.codingchallenge.components.InputCommandHandler;
import com.navi.codingchallenge.models.Request;

import java.io.File;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        int first = 0;
        String filePath = args[first];
        File file = new File(filePath);
        parseAndProcessInput(file);

    }

    public static void parseAndProcessInput(File file) {
        InputCommandHandler inputCommandHandler = new InputCommandHandler();
        try {
            Scanner inputScanner = new Scanner(file);
            while (inputScanner.hasNextLine()) {
                String inputLine = inputScanner.nextLine();
                Request request = inputCommandHandler.parseRequest(inputLine);
                inputCommandHandler.handleInput(request);
            }
            inputCommandHandler.processRequests();
            inputCommandHandler.printOutput();
            inputScanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
