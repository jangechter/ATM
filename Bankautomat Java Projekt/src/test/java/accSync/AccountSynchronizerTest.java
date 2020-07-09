/*
 * AccountSynchronizerTest.java
 *
 * Created on 2020-07-09
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package accSync;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import Exceptions.AccountSynchronisationException;
import Exceptions.ClientParsingException;
import accountSynchronizer.AccountSynchronizer;
import client.Client;
import repositories.ClientRepository;
import testData.TestData;

class AccountSynchronizerTest extends TestData {

    @Test
    void performAnAccountSynchronisationSuccess() throws ClientParsingException {

        ClientRepository cr = new ClientRepository();

        Client clientA = cr.findClient("AccountSyncClientA");
        Client clientB = cr.findClient("AccountSyncClientB");

        clientA.setBankBalance(BigDecimal.valueOf(5000));
        clientB.setBankBalance(BigDecimal.valueOf(5000));

        cr.persistClient(clientA);
        cr.persistClient(clientB);

        AccountSynchronizer as = new AccountSynchronizer(clientA);

        as.sychronizeAccounts("AccountSyncClientB", BigDecimal.valueOf(1000));

        assertEquals(cr.findClient("AccountSyncClientA").getBankBalance(), BigDecimal.valueOf(4000.));
        assertEquals(cr.findClient("AccountSyncClientB").getBankBalance(), BigDecimal.valueOf(6000.));

        //reset test-data
        cr.persistClient(clientA);
        cr.persistClient(clientB);
    }

    @Test
    void performAnAccountSynchronisationThrowsAccSyncException() throws ClientParsingException {

        ClientRepository cr = new ClientRepository();

        Client clientA = cr.findClient("AccountSyncClientA");
        Client clientB = cr.findClient("AccountSyncClientC");

        AccountSynchronizer as = new AccountSynchronizer(clientA);

        assertThrows(AccountSynchronisationException.class,
                     () -> as.sychronizeAccounts("AccountSyncClientC", BigDecimal.valueOf(1000)));
    }

    @Test
    void performAnAccountSynchronisationThrowsClientParsingException() throws ClientParsingException {

        ClientRepository cr = new ClientRepository();

        Client clientA = cr.findClient("AccountSyncClientA");

        AccountSynchronizer as = new AccountSynchronizer(clientA);

        assertThrows(AccountSynchronisationException.class,
                     () -> as.sychronizeAccounts("DE00 0000 0000 0000 0000 00", BigDecimal.valueOf(1000)));
    }
}
