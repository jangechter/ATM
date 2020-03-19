/*
 * Authentication.java
 *
 * Created on 2020-03-19
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package authentication;

import client.Client;
import clientRepository.ClientRepository;

public class Authentication {

    private final ClientRepository cr = new ClientRepository();
    private Client client = null;

    public Client getClient() {
        return client;
    }

    //überarbeiten
    public boolean logIn(final String iban, final String pin) {

        client = cr.findClient(iban);

        if ((client != null) && client.isActive()) {

            if (client.getNumberAttempts() < 3) {
                if (client.getPin().equals(pin)) {
                    return true;
                } else {
                    client.setNumberAttempts(client.getNumberAttempts() + 1);

                    if (!(client.getNumberAttempts() < 3)) {
                        client.setActive(false);
                    }

                    //TODO CSV.writeClient to persist the Attempts
                }
            }
        }

        return false;
    }
}
