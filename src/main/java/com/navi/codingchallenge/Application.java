package com.navi.codingchallenge;

import com.navi.codingchallenge.InputCommandHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inputScanner = new Scanner(System.in);
        InputCommandHandler inputCommandHandler;
        // file path= C:\Users\Vishal\Desktop\NAVI_INPUT.TXT
        try {

            String filePath = inputScanner.nextLine();
            File file = new File(filePath);
            inputScanner = new Scanner(file);
            inputCommandHandler = new InputCommandHandler();
            while (inputScanner.hasNextLine()) {
                String inputLine = inputScanner.nextLine();
                inputCommandHandler.handleInput(inputLine);
            }
            inputScanner.close();

        } catch (Exception e) {
            e.printStackTrace();
            inputScanner.close();
        }

    }
}
