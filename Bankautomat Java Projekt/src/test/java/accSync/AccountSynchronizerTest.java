/*
 * AccountSynchronizerTest.java
 *
 * Created on 2020-07-03
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package accSync;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import Exceptions.AccountSynchronisationException;
import Exceptions.ClientParsingException;
import client.Client;
import repositories.ClientRepository;
import testData.TestData;

class AccountSynchronizerTest extends TestData {

    @Test
    void performAnAccountSynchronisationSuccess() throws ClientParsingException {

        ClientRepository cr = new ClientRepository();

        Client clientA = cr.findClient("AccountSyncClientA");
        Client clientB = cr.findClient("AccountSyncClientB");

        BigDecimal bankBalance = clientA.getBankBalance().subtract(BigDecimal.valueOf(1000));
        BigDecimal bankBalance2 = clientB.getBankBalance().add(BigDecimal.valueOf(1000));

        clientA.setBankBalance(bankBalance);
        clientB.setBankBalance(bankBalance2);

        AccountSynchronizer as = new AccountSynchronizer(clientA);

        as.sychronizeAccounts("AccountSyncClientB", BigDecimal.valueOf(1000));

        System.out.println("Client A: " + clientA.getIban());
        System.out.println("Client B: " + clientB.getIban());

        assertThat(clientA.equals(cr.findClient(clientA.getIban()))).isTrue();
        assertThat(clientB.equals(cr.findClient(clientB.getIban()))).isTrue();
    }

    @Test
    void tryToPerformAnAccountSynchronisationThrowAccSyncException() throws ClientParsingException {

        ClientRepository cr = new ClientRepository();

        Client clientA = cr.findClient("AccountSyncClientA");
        Client clientB = cr.findClient("AccountSyncClientC");

        AccountSynchronizer as = new AccountSynchronizer(clientA);

        assertThrows(AccountSynchronisationException.class,
                     () -> as.sychronizeAccounts("AccountSyncClientC", BigDecimal.valueOf(1000)));
    }
}
