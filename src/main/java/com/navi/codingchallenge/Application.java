package com.navi.codingchallenge;

import com.navi.codingchallenge.components.InputCommandHandler;
import com.navi.codingchallenge.models.Request;

import java.io.File;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        InputCommandHandler inputCommandHandler;
        try {
            String filePath = args[0];
            File file = new File(filePath);
            Scanner inputScanner = new Scanner(file);
            inputCommandHandler = new InputCommandHandler();
            while (inputScanner.hasNextLine()) {
                String inputLine = inputScanner.nextLine();
                Request request = inputCommandHandler.parseRequest(inputLine);
                inputCommandHandler.handleInput(request);
            }
            inputCommandHandler.processRequests();
            inputCommandHandler.printOutput();
            inputScanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
