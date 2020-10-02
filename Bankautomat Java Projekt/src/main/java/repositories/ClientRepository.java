/*
 * ClientRepository.java
 *
 * Created on 2020-10-02
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package repositories;

import Exceptions.ClientParsingException;
import client.Client;
import csvReader.CSVReader;
import csvWriter.CSVWriter;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class ClientRepository {

    private static final java.lang.String CLIENTS = "/Clients/";
    private static final java.lang.String CSV = ".csv";

    private File createFileByIBAN(final java.lang.String iban) {

        final java.lang.String path = System.getProperty("user.dir") + CLIENTS + iban + "/" + iban + CSV;

        return new File(path);
    }

    public Client findClient(final java.lang.String iban) throws ClientParsingException {

        final Client client;
        final List<java.lang.String> fileValues;
        final java.lang.String[] clientValues;

        try {

            fileValues = CSVReader.readCSVFile(createFileByIBAN(iban));

            clientValues = fileValues.get(0).split(",");

            client = new Client(clientValues[0], clientValues[1], clientValues[2], clientValues[3], BigDecimal.valueOf(
                    Double.parseDouble(clientValues[4])), Boolean.parseBoolean(clientValues[5]),
                                Integer.valueOf(clientValues[6]));

            return client;
        } catch (final IOException e) {
            System.out.println("Cannot find this iban");
        } catch (final IllegalArgumentException e) {
            throw new ClientParsingException("cannot parse client with this data");
        }

        return null;
    }

    public void persistClient(final Client client) {

        try {
            CSVWriter.writeClient(client);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
