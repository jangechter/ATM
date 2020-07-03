/*
 * CSVWriter.java
 *
 * Created on 2020-07-03
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package csvWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import cashTransfer.CashTransfer;
import cashbox.Cashbox;
import client.Client;

public class CSVWriter {

    private CSVWriter() {
    }

    public static void writeClient(Client client) throws IOException {

        final PrintWriter pw;

        if (client != null) {
            final java.lang.String header = "Name,Firstname,Iban ,Pin ,Bank Balance,Status,AnzahlVersuche";
            final java.lang.String clientInformation = client.getName() + "," +
                                                       client.getFirstName() + "," +
                                                       client.getIban() + "," +
                                                       client.getPin() + "," +
                                                       client.getBankBalance().toString() + "," +
                                                       client.isActive() + "," +
                                                       client.getNumberAttempts();

            pw = new PrintWriter(
                    new File(System.getProperty("user.dir") + "/Clients/" + client.getIban() + "/" + client.getIban()
                             + ".csv"));

            pw.println(header);
            pw.println(clientInformation);

            pw.close();
        } else {
            throw new IllegalArgumentException("Client is null");
        }
    }

    public static void writeCashbox(Cashbox cb) throws FileNotFoundException {

        final PrintWriter pw;

        if (cb != null) {

            final java.lang.String header = "Moneynote,quantity";

            pw = new PrintWriter(
                    new File(System.getProperty("user.dir") + "/Cashbox/" + "CashboxNotes" + ".csv"));

            pw.println(header);

            cb.getNotes().forEach((moneynote, integer) -> pw.println(moneynote.getValue() + "," + integer));

            pw.close();
        } else {
            throw new IllegalArgumentException("Cashbox is null");
        }
    }

    public static void writeCashTransfer(CashTransfer cf, File file) throws IOException {

        if (cf != null) {

            try (final BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));) {

                if (file.length() != 0) {

                    bw.append(
                            cf.getTransactionID() + "," + cf.getRecipientIBAN() + "," + cf.getApplicantIBAN() + "," + cf
                                    .getAmount() + "," + cf.getDate() + "," + cf.getPurpose());
                    bw.newLine();
                } else {

                    String header = "TranactionID, RecipientIBAN, ApplicantIBAN, Amount, Date, Purpose";

                    bw.write(header);

                    bw.newLine();

                    bw.append(
                            cf.getTransactionID() + "," + cf.getRecipientIBAN() + "," + cf.getApplicantIBAN() + "," + cf
                                    .getAmount() + "," + cf.getDate() + "," + cf.getPurpose());
                    bw.newLine();
                }
            }
        } else {
            throw new IllegalArgumentException("CashTransfer is null");
        }
    }
}
