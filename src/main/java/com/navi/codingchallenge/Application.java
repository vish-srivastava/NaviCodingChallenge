package com.navi.codingchallenge;

import com.navi.codingchallenge.InputCommandHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        InputCommandHandler inputCommandHandler;
        try {

            String filePath = args[0];
            File file = new File(filePath);
            Scanner inputScanner = new Scanner(file);
            inputCommandHandler = new InputCommandHandler();
            while (inputScanner.hasNextLine()) {
                String inputLine = inputScanner.nextLine();
                inputCommandHandler.handleInput(inputLine);
            }
            inputScanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
