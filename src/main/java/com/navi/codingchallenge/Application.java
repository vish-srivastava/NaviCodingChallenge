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
            System.out.println("Enter File Path or 0 to exit");
            String filePath = inputScanner.nextLine();
            while (!filePath.equalsIgnoreCase("0")) {
                File file = new File(filePath);
                inputScanner = new Scanner(file);
                inputCommandHandler = new InputCommandHandler();
                while (inputScanner.hasNextLine()) {
                    String inputLine = inputScanner.nextLine();
                    inputCommandHandler.handleInput(inputLine);
                }
                inputScanner = new Scanner(System.in);
                System.out.println("Enter File Path or 0 to exit");
                filePath = inputScanner.nextLine();
            }
            inputScanner.close();
            if (filePath.equalsIgnoreCase("0")) {
                System.out.print("Exiting Program in ");
                Thread.sleep(700);
                System.out.print("3..");
                Thread.sleep(700);
                System.out.print("2..");
                Thread.sleep(700);
                System.out.print("1");
                System.out.println();
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            inputScanner.close();
        }

    }
}
