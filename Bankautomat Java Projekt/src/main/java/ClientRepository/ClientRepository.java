/*
 * ClientRepository.java
 *
 * Created on 2020-03-03
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package ClientRepository;

import java.io.File;
import java.util.ArrayList;

import client.Client;
import csvReader.CSVReader;

public class ClientRepository {

    private Client client;
    private ArrayList<Client> clientList = new ArrayList<>();
    private CSVReader csvReader = new CSVReader();

    public Client findClient(String iban){

        String path =  System.getProperty("user.dir") + "/src/test/resources/" + iban + ".csv";

        client = csvReader.read_Client(new File(path));

        return client;

    }

}
