/*
 * CreateUserGUI.java
 *
 * Created on 2020-04-23
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui;

import java.io.IOException;

import consoleInput.ConsoleInput;

public class CreateUserGUI {


    public void printCreateUserGUI() throws IOException {

        System.out.println("----------------------------");
        System.out.println("Create User:");
        System.out.println("Enter Name:");

        String name = ConsoleInput.readConsoleInput();

        System.out.println("Enter Firstname:");

        String firstName = ConsoleInput.readConsoleInput();

    }

}
