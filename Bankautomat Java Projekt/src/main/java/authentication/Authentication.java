/*
 * Authentication.java
 *
 * Created on 2020-03-16
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package authentication;

import client.Client;
import clientRepository.ClientRepository;

public class Authentication {

    private final ClientRepository cr = new ClientRepository();
    private Client client = null;
    private Integer numberAttempts = 0;
    private boolean isClientLoggedIN = false;

    public Integer getNumberAttempts() {
        return numberAttempts;
    }

    public boolean isClientLoggedIN() {
        return isClientLoggedIN;
    }

    public Client getClient() {
        return client;
    }

    public boolean logIn(final String iban, final String pin) {

        client = cr.findClient(iban);

        if ((!isClientLoggedIN) && (client != null)) {

            if (numberAttempts < 3) {
                if (client.getPin().equals(pin)) {
                    isClientLoggedIN = true;
                    return true;
                } else {
                    numberAttempts++;
                }
            } else {
                client.setActive(false);
                //TODO CSV.writeClient for save the blocking of the Account
            }
        }

        return false;
    }
}
