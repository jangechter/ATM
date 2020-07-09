/*
 * CashTransferRepository.java
 *
 * Created on 2020-07-09
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

import com.google.common.annotations.VisibleForTesting;

import cashTransfer.CashTransfer;
import client.Client;
import csvReader.CSVReader;
import csvWriter.CSVWriter;

public class CashTransferRepository {

    private static final String CLIENTS = "/Clients/";
    private final Client client;
    private List<CashTransfer> cTFList = new ArrayList<>();
    private final File cTfFromClient;

    public CashTransferRepository(final Client client) {
        this.client = client;

        cTfFromClient = new File(
                System.getProperty("user.dir") + CLIENTS + "/" + client.getIban() + "/" + client.getIban()
                + "_Cash_Transfers.csv");

        initiateRepository();
    }

    private void initiateRepository() {

        CashTransfer tempCf;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {

            if (cTfFromClient.exists()) {

                for (final String line : CSVReader.readCSVFile(cTfFromClient)) {

                    String[] cfData = line.split(",");

                    if (!(line.equals(""))) {

                        String transactionID = cfData[0];
                        String recipientIBAN = cfData[1];
                        String applicantIBAN = cfData[2];
                        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(cfData[3]));

                        LocalDateTime date = LocalDateTime.parse(cfData[4].replace('T', ' ').substring(0, 16),
                                                                 formatter);
                        String purpose = cfData[5];

                        tempCf = new CashTransfer(transactionID, recipientIBAN, applicantIBAN, amount, date, purpose);

                        cTFList.add(tempCf);
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

    public void addCashTransfer(CashTransfer cashTransfer) {

        if (!cTFList.contains(cashTransfer)) {
            cTFList.add(cashTransfer);
            persistCashTransfer(cashTransfer);
        } else {
            throw new IllegalArgumentException("no duplicated cash transfers");
        }
    }

    public CashTransfer findCashTarnsfer(String transactionID) {

        return cTFList.stream().filter(ct -> ct.getTransactionID().equals(transactionID)).findFirst().get();
    }

    @VisibleForTesting
    private void persistCashTransfer(CashTransfer cf) {

        try {
            CSVWriter.writeCashTransfer(cf, cTfFromClient);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
