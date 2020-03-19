/*
 * ClientRepository.java
 *
 * Created on 2020-03-19
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package clientRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jetbrains.annotations.Nullable;

import client.Client;
import csvReader.CSVReader;

public class ClientRepository {

    private static final String SRC_MAIN_RESOURCES = "/src/main/resources/";
    private static final String SRC_TEST_RESOURCES = "/src/test/resources/";
    private static final String CSV = ".csv";
    private final CSVReader reader = new CSVReader();

    File findFileByIBAN(String iban) throws FileNotFoundException {

        String path = System.getProperty("user.dir") + SRC_MAIN_RESOURCES + iban + CSV;

        final File file = new File(path);

        if (!file.canRead()) {
            path = System.getProperty("user.dir") + SRC_TEST_RESOURCES + iban + CSV;
            final File testFile = new File(path);

            if (!testFile.canRead()) {
                throw new FileNotFoundException("Invalid IBAN");
            }

            return testFile;
        }

        return file;
    }

    public @Nullable Client findClient(final String iban) {

        try {

            return reader.readClient(findFileByIBAN(iban));
        } catch (final IOException e) {
            e.printStackTrace();

            return null;
        }
    }
}
