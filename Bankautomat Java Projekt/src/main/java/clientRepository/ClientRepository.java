/*
 * ClientRepository.java
 *
 * Created on 2020-05-07
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package clientRepository;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import Exceptions.ClientParsingException;
import client.Client;
import csvReader.CSVReader;
import csvWriter.CSVWriter;

public class ClientRepository {

    private static final String CLIENTS = "/Clients/";
    private static final String CSV = ".csv";

    private File createFileByIBAN(final String iban) {

        final String path = System.getProperty("user.dir") + CLIENTS + iban + CSV;

        return new File(path);
    }

    public Client findClient(final String iban) throws ClientParsingException {

        Client client;
        final List<String> fileValues;
        final String[] clientValues;

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
