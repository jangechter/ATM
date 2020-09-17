/*
 * CashTransferRepository.java
 *
 * Created on 2020-09-17
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package repositories;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.common.annotations.VisibleForTesting;

import cashTransfer.CashTransfer;
import client.Client;
import csvReader.CSVReader;
import csvWriter.CSVWriter;

public class CashTransferRepository {

    private static final String CLIENTS = "/Clients/";
    private final List<CashTransfer> cTFList = new ArrayList<>();
    private final File cTfFromClient;

    public CashTransferRepository(final Client client) {

        cTfFromClient = new File(
                System.getProperty("user.dir") + CLIENTS + "/" + client.getIban() + "/" + client.getIban()
                + "_Cash_Transfers.csv");

        initiateRepository();
    }

    private void initiateRepository() {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {

            if (cTfFromClient.exists()) {

                for (final String line : CSVReader.readCSVFile(cTfFromClient)) {

                    final String[] cfData = line.split(",");

                    if (!(line.isEmpty())) {

                        final String transactionID = cfData[0];
                        final String recipientIBAN = cfData[1];
                        final String applicantIBAN = cfData[2];
                        final BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(cfData[3]));

                        final LocalDateTime date = LocalDateTime.parse(cfData[4].replace('T', ' ').substring(0, 16),
                                                                       formatter);

                        LocalDateTime.now().minusHours(2);

                        String start = LocalDateTime.now().minusHours(2).toString() + "Z";
                        String end = LocalDateTime.now().minusHours(2).toString() + "Z";

                        final String purpose = cfData[5];

                        cTFList.add(
                                new CashTransfer(transactionID, recipientIBAN, applicantIBAN, amount, date, purpose));
                    }
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public List<CashTransfer> getCashTransfers() {

        return Collections.unmodifiableList(cTFList);
    }

    public void addCashTransfer(final CashTransfer cashTransfer) {

        if (!cTFList.contains(cashTransfer)) {
            cTFList.add(cashTransfer);
            persistCashTransfer(cashTransfer);
        } else {
            throw new IllegalArgumentException("no duplicated cash transfers");
        }
    }

    public Optional<CashTransfer> findCashTarnsfer(final String transactionID) {

        final Optional<CashTransfer> ctf = cTFList.stream()
                                                  .filter(ct -> ct.getTransactionID().equals(transactionID))
                                                  .findFirst();

        if (ctf.isPresent()) {
            return ctf;
        }

        return Optional.empty();
    }

    @VisibleForTesting
    private void persistCashTransfer(final CashTransfer cf) {

        try {
            CSVWriter.writeCashTransfer(cf, cTfFromClient);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
