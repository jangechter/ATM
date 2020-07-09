/*
 * ConsoleInput.java
 *
 * Created on 2020-07-09
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package consoleInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {

    private ConsoleInput() {
    }

    public static String readConsoleInput() throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        return br.readLine();
    }

    public static Double readDoubleInput() {

        Double input;

        do {
            try {

                input = Double.parseDouble(readConsoleInput());
            } catch (final NumberFormatException | IOException e) {
                System.out.println("Please enter a double number");
                input = null;
            }
        } while (input == null);

        return input;
    }

    public static Integer readIntegerInput() {

        Integer input;

        do {
            try {

                input = Integer.parseInt(readConsoleInput());
            } catch (final NumberFormatException | IOException e) {
                System.out.println("Please enter an integer number");
                input = null;
            }
        } while (input == null);

        return input;
    }
}
