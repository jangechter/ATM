/*
 * Authentication.java
 *
 * Created on 2020-03-03
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package authentication;

import ClientRepository.ClientRepository;
import client.Client;

public class Authentication {

    private ClientRepository cr = new ClientRepository();
    private Client client;

    public boolean logIn(String iban, String pin){

        client = cr.findClient(iban);

        if(client != null){

            if(client.getPin().equals(pin)){
                return true;
            }else{
                client = null;
            }

        }
                return false;
    }

    public Client getClient() throws NullPointerException{
            return client;
    }
}
