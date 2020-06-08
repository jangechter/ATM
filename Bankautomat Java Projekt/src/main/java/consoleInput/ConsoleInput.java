/*
 * ConsoleInput.java
 *
 * Created on 2020-06-08
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

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        return br.readLine();
    }

    public static Integer readIntegerInput() throws IOException {

        Integer input;

        do {
            try {

                input = Integer.parseInt(readConsoleInput());
            } catch (final NumberFormatException e) {
                System.out.println("Please enter an integer number");
                input = null;
            }
        } while (input == null);

        return input;
    }
}
