/*
 * Authentication.java
 *
 * Created on 2020-10-02
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package authentication;

import Exceptions.AuthenticationException;
import Exceptions.ClientParsingException;
import client.Client;
import repositories.ClientRepository;

public class Authentication {

    private final ClientRepository repository = new ClientRepository();
    private Client client;

    public Client getClient() {
        return client;
    }

    public boolean logIn(final java.lang.String iban, final java.lang.String pin) throws AuthenticationException {

        try {

            client = repository.findClient(iban);
        } catch (ClientParsingException e) {
            e.printStackTrace();
        }

        if ((client != null) && client.isActive()) {

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

                throw new AuthenticationException("invalid credentials");
            }
        } else {
            throw new AuthenticationException("client is not active");
        }
    }

    public void persistClient() {

        repository.persistClient(client);
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
