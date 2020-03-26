/*
 * CSVReader.java
 *
 * Created on 2020-03-25
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package csvReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import Exceptions.ClientParsingException;
import cashbox.Cashbox;
import client.Client;
import moneynote.Moneynote;

public class CSVReader {

    public static Client readClient(final File file) throws IOException, ClientParsingException {

        final Client client;
        final String[] values;
        final BufferedReader br = new BufferedReader(new FileReader(file));

        br.readLine(); // skip first line

        values = br.readLine().split(",");  // read line with client data

        br.close();

        client = new Client(values[0], values[1], values[2], values[3], BigDecimal.valueOf(
                Double.parseDouble(values[4])), Boolean.parseBoolean(values[5]), Integer.valueOf(values[6]));

        return client;
    }

    public static Cashbox readCashbox(final File file) throws IOException {

        final Cashbox cashbox;
        final String[] values;
        final BufferedReader br = new BufferedReader(new FileReader(file));

        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        br.readLine();

        values = br.readLine().split(";");

        for (final String s : values) {
            if (!s.equals("") && !s.contains("-")) {
                notes.put(new Moneynote(Integer.parseInt(s.split(",")[0].trim())),
                          Integer.parseInt(s.split(",")[1].trim()));
            }
        }

        cashbox = new Cashbox(notes);

        return cashbox;
    }
}
