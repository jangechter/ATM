/*
 * ATM.java
 *
 * Created on 2020-04-20
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package atm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import authentication.Authentication;
import cashbox.Cashbox;
import csvReader.CSVReader;
import moneynote.Moneynote;

public class ATM {

    private Authentication loggedInClient = new Authentication();
    private Cashbox cashbox;
    private final String CASHBOX = "/Cashbox/CashboxNotes.csv";

    private String currency;

    public ATM() {

        try {
            cashbox = CSVReader.readCashbox(new File(System.getProperty("user.dir") + CASHBOX));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();

        try (BufferedReader r = new BufferedReader(
                new FileReader(new File(System.getProperty("user.dir") + "/properties/properties.env")))) {

            properties.load(r);
        } catch (IOException e) {

            e.printStackTrace();
        }

        currency = (String) properties.get("currency");
    }

    private BigDecimal convertMoneynotesToAmount(final HashMap<Moneynote, Integer> notes) {

        long amountTemp = 0;

        List<Moneynote> moneynotes = notes.keySet().stream().sorted().collect(Collectors.toList());

        for (Moneynote m : moneynotes) {

            amountTemp += m.getValue() * notes.get(m);
        }

        return BigDecimal.valueOf(amountTemp);
    }

    public Optional<HashMap<Moneynote, Integer>> withdrawMoney(final Integer amount) {

        final HashMap<Moneynote, Integer> withdrawNotes;

        if (loggedInClient != null) {

            if (isLoggedInClientsBankBalanceValid(new BigDecimal(amount))) {

                withdrawNotes = cashbox.withdraw(amount);

                loggedInClient.getClient().setBankBalance(
                        loggedInClient.getClient().getBankBalance().subtract(convertMoneynotesToAmount(withdrawNotes)));

                loggedInClient.persistClient();

                return Optional.of(withdrawNotes);
            }
        }

        return Optional.empty();
    }

    public void depositMoney(final HashMap<Moneynote, Integer> amount) {

        if (loggedInClient.getClient() != null) {
            cashbox.deposit(amount);

            loggedInClient.getClient().setBankBalance(
                    loggedInClient.getClient().getBankBalance().add(convertMoneynotesToAmount(amount)));

            loggedInClient.persistClient();
        }
    }

    public boolean login(String iban, String pin) {

        return loggedInClient.logIn(iban, pin);
    }

    public boolean logout() {

        return loggedInClient.logout();
    }

    public Authentication getLoggedInClient() {
        return loggedInClient;
    }

    public String getCurrency() {
        return currency;
    }

    public Cashbox getCashbox() {
        return cashbox;
    }

    public void setLoggedInClient(final Authentication loggedInClient) {
        this.loggedInClient = loggedInClient;
    }

    private boolean isLoggedInClientsBankBalanceValid(final BigDecimal amount) {

        return (loggedInClient.getClient().getBankBalance().doubleValue() - amount.doubleValue()) >= 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ATM atm = (ATM) o;
        return Objects.equals(loggedInClient, atm.loggedInClient) &&
               cashbox.equals(atm.cashbox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loggedInClient, cashbox);
    }
}
