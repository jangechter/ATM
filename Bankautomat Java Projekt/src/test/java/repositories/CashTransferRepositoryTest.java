/*
 * CashTransferRepositoryTest.java
 *
 * Created on 2020-07-09
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package repositories;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import Exceptions.ClientParsingException;
import cashTransfer.CashTransfer;
import client.Client;
import testData.TestData;

class CashTransferRepositoryTest extends TestData {

    @AfterEach
    private void resetFile() {

        final File cfFile = new File(
                System.getProperty("user.dir") + CLIENTS + TEST_APPLICANT_IBAN + "/" + TEST_APPLICANT_IBAN
                + "_Cash_Transfers.csv");

        cfFile.delete();
    }

    @Test
    void loadCashTransfersFromClientSuccessfully() throws ClientParsingException {

        final String str = "1986-04-08 12:30";

        final ClientRepository cr = new ClientRepository();

        final Client client = cr.findClient(TEST_APPLICANT_IBAN);

        client.getCashRepository().addCashTransfer(TEST_CASH_TRANSFER);

        final CashTransfer ctf = client.getCashRepository().findCashTarnsfer(TEST_TRANSACTION_ID);

        assertThat(ctf.equals(TEST_CASH_TRANSFER)).isTrue();

    }

    @Test
    void addCashTransferSuccessfully() throws ClientParsingException {

        final ClientRepository cr = new ClientRepository();

        final Client client = cr.findClient(TEST_APPLICANT_IBAN);

        client.getCashRepository().addCashTransfer(TEST_CASH_TRANSFER);

        assertThat(client.getCashRepository().getCashTransfers().size() == 1).isTrue();
    }

    @Test
    void initiateCashTransferRepositorySuccessfully() throws ClientParsingException {

        final ClientRepository cr = new ClientRepository();
        final Client clientA = cr.findClient("AccountSyncClientA");

        assertThat(clientA.getCashRepository().getCashTransfers().size()).isGreaterThan(0);
    }

    @Test
    void addCashTransferThrowsIlleagalArgumentException() throws ClientParsingException {

        final ClientRepository cr = new ClientRepository();

        final Client client = cr.findClient(TEST_APPLICANT_IBAN);

        client.getCashRepository().addCashTransfer(TEST_CASH_TRANSFER);

        assertThrows(IllegalArgumentException.class,
                     () -> client.getCashRepository().addCashTransfer(TEST_CASH_TRANSFER));
    }
}
