/*
 * CSVWriter.java
 *
 * Created on 2020-03-25
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package csvWriter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import client.Client;

public class CSVWriter {

    public static void writeClient(Client client) throws IOException, NullPointerException {

        final PrintWriter pw;

        if (client != null) {
            final String header = "Name,Firstname,Iban ,Pin ,Bank Balance,Status,AnzahlVersuche";
            final String clientInformation = client.getName() + "," +
                                             client.getFirstName() + "," +
                                             client.getIban() + "," +
                                             client.getPin() + "," +
                                             client.getBankBalance().toString() + "," +
                                             client.isActive() + "," +
                                             client.getNumberAttempts();

            pw = new PrintWriter(
                    new File(System.getProperty("user.dir") + "/Clients/" + client.getIban() + ".csv"));

            pw.println(header);
            pw.println(clientInformation);

            pw.close();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
