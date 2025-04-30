package com.pluralsight;

import java.util.Scanner;

public class Console {

    Scanner scanner = new Scanner(System.in);


    public int promptForInt(String prompt) {
        boolean hasResult = false;
        int result = -1;
        while(!hasResult){
            try {

                System.out.print(prompt);
                result = scanner.nextInt();
                scanner.nextLine();
                hasResult = true;

            } catch (Exception e) {
                System.out.println("Not a valid option, please try again");
                scanner.next();


            }
        }

        return result;

    }

    public float promptForFloat(String prompt) {
        boolean hasResult = false;
        float result = -1;

        while (!hasResult) {
            try {
                System.out.print(prompt);
                result = scanner.nextFloat();
                return result;
            } catch (Exception e) {
                System.out.println("Not a valid input, please enter a valid float.");
            }
        }
        return result;
    }


    public double promptForDouble(String prompt) {
        boolean hasResult = false;
        double result = -1;

        while (!hasResult) {
            try {
                System.out.print(prompt);
                result = scanner.nextDouble();
                scanner.nextLine();
                return result;
            } catch (Exception e) {
                System.out.println("Not a valid input, please enter a valid double.");
            }
        }
        return result;
    }

    public String promptForString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine().trim();


    }
}
