/*
 * CashTransferRepository.java
 *
 * Created on 2020-06-25
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package repositories;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cashTransfer.CashTransfer;
import client.Client;
import csvReader.CSVReader;
import csvWriter.CSVWriter;

public class CashTransferRepository {

    private static final String CLIENTS = "/Clients/";
    private final Client client;
    private List<CashTransfer> cfList = new ArrayList<>();
    private final File cfFromClient;

    public CashTransferRepository(final Client client) {
        this.client = client;

        cfFromClient = new File(
                System.getProperty("user.dir") + CLIENTS + "/" + client.getIban() + "/" + client.getIban()
                + "_Cash_Transfers.csv");

        initiateRepository();
    }

    private void initiateRepository() {

        CashTransfer tempCf;

        try {
            for (String line : CSVReader.readCSVFile(cfFromClient)) {

                String[] cfData = line.split(",");

                String transactionID = cfData[0];
                String recipientIBAN = cfData[1];
                String applicantIBAN = cfData[2];
                BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(cfData[3]));
                LocalDateTime date = LocalDateTime.MIN;
                String purpose = cfData[5];
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /*
    public CashTransfer findCashTarnsfer(){
        return new CashTransfer()
    }
    */
    public void persistCashTransfer(CashTransfer cf) {

        try {
            CSVWriter.writeCashTransfer(cf, cfFromClient);
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
