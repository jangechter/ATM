/*
 * ClientRepository.java
 *
 * Created on 2020-03-03
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package clientRepository;

import java.io.File;
import java.io.IOException;

import client.Client;
import csvReader.CSVReader;

public class ClientRepository {

    private CSVReader csvReader = new CSVReader();

    public Client findClient(final String iban) {

        final String path = System.getProperty("user.dir") + "/src/main/resources/" + iban + ".csv";

        try {
            return csvReader.readClient(new File(path));
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }
}
