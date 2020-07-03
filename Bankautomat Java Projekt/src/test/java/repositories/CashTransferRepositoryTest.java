/*
 * CashTransferRepositoryTest.java
 *
 * Created on 2020-07-03
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package repositories;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import Exceptions.ClientParsingException;
import cashTransfer.CashTransfer;
import client.Client;
import testData.TestData;

class CashTransferRepositoryTest extends TestData {

    @Test
    void loadCashTransfersFromClient_successfully() throws ClientParsingException {

        String str = "1986-04-08 12:30";

        ClientRepository cr = new ClientRepository();

        Client client = cr.findClient(TEST_APPLICANT_IBAN);

        client.getCashRepository().addCashTransfer(TEST_CASH_TRANSFER);

        CashTransfer ctf = client.getCashRepository().findCashTarnsfer(TEST_TRANSACTION_ID);

        assertThat(ctf.equals(TEST_CASH_TRANSFER)).isTrue();
    }

    @Test
    void addCashTransfer() throws ClientParsingException {

        ClientRepository cr = new ClientRepository();

        Client client = cr.findClient(TEST_APPLICANT_IBAN);

        client.getCashRepository().addCashTransfer(TEST_CASH_TRANSFER);

        assertThat(client.getCashRepository().getCashTransfers().size() == 1).isTrue();
    }
}
