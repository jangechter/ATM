/*
 * Authentication.java
 *
 * Created on 2020-04-20
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package authentication;

import client.Client;
import clientRepository.ClientRepository;

public class Authentication {

    private final ClientRepository repository = new ClientRepository();
    private Client client;

    public Client getClient() {
        return client;
    }

    //überarbeiten
    public boolean logIn(final String iban, final String pin) {

        client = repository.findClient(iban);

        if ((client != null) && client.isActive()) {

            if (client.getNumberAttempts() < 3) {
                if (client.getPin().equals(pin)) {
                    client.setNumberAttempts(0);
                    repository.persistClient(client);
                    return true;
                } else {
                    client.setNumberAttempts(client.getNumberAttempts() + 1);

                    if (!(client.getNumberAttempts() < 3)) {
                        client.setActive(false);
                    }

                    repository.persistClient(client);
                }
            }
        }

        return false;
    }

    public boolean logout() {

        if (client != null) {
            client = null;
            return true;
        } else {
            return false;
        }
    }
}
