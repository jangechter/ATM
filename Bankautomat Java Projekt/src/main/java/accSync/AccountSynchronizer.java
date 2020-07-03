/*
 * AccountSynchronizer.java
 *
 * Created on 2020-07-03
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package accSync;

import java.math.BigDecimal;

import Exceptions.AccountSynchronisationException;
import Exceptions.ClientParsingException;
import client.Client;
import repositories.ClientRepository;

public class AccountSynchronizer {

    private ClientRepository clientRepository = new ClientRepository();
    private Client loggedInClient;
    private Client recipientClient;

    public AccountSynchronizer(final Client loggedInClient) {
        this.loggedInClient = loggedInClient;
    }

    private boolean checkRecipientAvailability(String recipientIBAN) {

        try {
            recipientClient = clientRepository.findClient(recipientIBAN);

            return recipientClient != null;
        } catch (ClientParsingException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean sychronizeAccounts(String recipientIBAN, BigDecimal amount) {

        if (checkRecipientAvailability(recipientIBAN)) {

            BigDecimal newBankBalanceAfterAdd = recipientClient.getBankBalance().add(amount);

            recipientClient.setBankBalance(newBankBalanceAfterAdd);

            clientRepository.persistClient(recipientClient);

            BigDecimal newBankBalanceAfterSub = loggedInClient.getBankBalance().subtract(amount);

            clientRepository.persistClient(loggedInClient);

            return true;
        } else {

            throw new AccountSynchronisationException("Recipient account not available for synchronisation");
        }
    }
}
