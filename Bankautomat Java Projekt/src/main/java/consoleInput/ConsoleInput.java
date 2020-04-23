/*
 * ConsoleInput.java
 *
 * Created on 2020-04-20
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package consoleInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class ConsoleInput {

    static public String readConsoleInput() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        return br.readLine();

    }

}
