/*
 * CSVReader.java
 *
 * Created on 2020-04-03
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package csvReader;

import java.io.BufferedReader;
import java.io.File;
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

        try {
            client = new Client(values[0], values[1], values[2], values[3], BigDecimal.valueOf(
                    Double.parseDouble(values[4])), Boolean.parseBoolean(values[5]), Integer.valueOf(values[6]));
        } catch (IllegalArgumentException e) {
            throw new ClientParsingException("cannot parse client with this data");
        }

        return client;
    }

    public static Cashbox readCashbox(final File file) throws IOException {

        final Cashbox cashbox;
        String[] values;
        final BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        br.readLine();

        do {

            line = br.readLine();

            if (line != null) {

                values = line.split(",");
                notes.put(new Moneynote(Integer.parseInt(values[0])), Integer.parseInt(values[1]));
            }
        } while (line != null);

        cashbox = new Cashbox(notes);

        return cashbox;
    }
}
