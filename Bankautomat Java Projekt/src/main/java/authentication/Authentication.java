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
    private Integer numberAttempts = 0;

    public Integer getNumberAttempts() {
        return numberAttempts;
    }

    public Client getClient() {
        return client;
    }

    //überarbeiten
    public boolean logIn(final String iban, final String pin) {

        client = cr.findClient(iban);

        if (client != null) {

            if (numberAttempts < 3) {
                if (client.getPin().equals(pin)) {
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
