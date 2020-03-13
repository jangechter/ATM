/*
 * CSVReader.java
 *
 * Created on 2020-03-13
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package csvReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import client.Client;

public class CSVReader {

    public Client read_Client(File file){

        Client client = null;
        final String[] values;

        try{
            try (final BufferedReader br = new BufferedReader(new FileReader(file))) {
                br.readLine(); // skip first line

                values = br.readLine().split(",");  // read line with client data
            }

            client = new Client(values[0], values[1], values[2], values[3], BigDecimal.valueOf(
                    Double.parseDouble(values[4])), Boolean.valueOf(values[5]));
        }catch(final IOException | IllegalArgumentException e){
            e.printStackTrace();
        }

        return client;

    }

}
