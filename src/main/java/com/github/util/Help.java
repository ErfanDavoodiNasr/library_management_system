package com.github.util;

import lombok.experimental.UtilityClass;

import java.util.Scanner;

@UtilityClass
public class Help {
    private final static Scanner scanner = new Scanner(System.in);

    public static <E> void println(E prompt) {
        System.out.println(prompt);
    }

    public static String input(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static Integer intInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return null;
        }
    }

    public static Double doubleInput(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return null;
        }
    }
}
