/*
 * AccountSynchronizer.java
 *
 * Created on 2020-09-21
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package accountSynchronizer;

import java.math.BigDecimal;

import Exceptions.AccountSynchronisationException;
import Exceptions.ClientParsingException;
import cashTransfer.CashTransfer;
import client.Client;
import repositories.ClientRepository;

public class AccountSynchronizer {

    private final ClientRepository clientRepository = new ClientRepository();
    private final Client loggedInClient;
    private Client recipientClient = null;

    public AccountSynchronizer(final Client loggedInClient) {
        this.loggedInClient = loggedInClient;
    }

    private boolean checkRecipientAvailability(final String recipientIBAN) {

        try {
            recipientClient = clientRepository.findClient(recipientIBAN);

            return recipientClient != null;
        } catch (final ClientParsingException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean sychronizeAccounts(final String recipientIBAN, final BigDecimal amount)
            throws AccountSynchronisationException {

        //TODO
        if (checkRecipientAvailability(recipientIBAN)) {

            final BigDecimal newBalanceAfterAdd = recipientClient.getBankBalance().add(amount);

            recipientClient.setBankBalance(newBalanceAfterAdd);

            clientRepository.persistClient(recipientClient);

            final BigDecimal newBalanceAfterSub = loggedInClient.getBankBalance().subtract(amount);

            loggedInClient.setBankBalance(newBalanceAfterSub);

            clientRepository.persistClient(loggedInClient);

            return true;
        } else {

            throw new AccountSynchronisationException("Recipient account not available for synchronisation");
        }
    }

    public void addCashTransferToClients(final CashTransfer repCtf, final CashTransfer appCtf) {

        recipientClient.getCashRepository().addCashTransfer(repCtf);

        loggedInClient.getCashRepository().addCashTransfer(appCtf);
    }
}
