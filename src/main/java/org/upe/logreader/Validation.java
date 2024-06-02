package org.upe.logreader;

import java.util.Scanner;

public class Validation {
    public static int validateInt(Scanner scanner, String txt) {
        while (true) {
            try {
                System.out.print(txt);
                String input = scanner.next();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("[ERRO de entrada] ");
            }
        }
    }
}
