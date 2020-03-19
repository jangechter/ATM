/*
 * ClientRepository.java
 *
 * Created on 2020-03-19
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package clientRepository;

import java.io.File;
import java.io.IOException;

import client.Client;
import csvReader.CSVReader;

public class ClientRepository {

    private final CSVReader reader = new CSVReader();

    private static final String CLIENTS = "/Clients/";
    private static final String CSV = ".csv";

    private File createFileByIBAN(final String iban) {

        final String path = System.getProperty("user.dir") + CLIENTS + iban + CSV;

        return new File(path);
    }

    public Client findClient(final String iban) {

        try {

            return CSVReader.readClient(createFileByIBAN(iban));
        } catch (final IOException e) {
        }
        return null;
    }
}
