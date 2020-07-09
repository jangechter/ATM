/*
 * ATM.java
 *
 * Created on 2020-07-09
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package atm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import accountSynchronizer.AccountSynchronizer;
import authentication.Authentication;
import cashTransfer.CashTransfer;
import cashbox.Cashbox;
import moneynote.Moneynote;

public class ATM {

    private Authentication loggedInClient = new Authentication();
    private Cashbox cashbox;
    private String currency;
    private AccountSynchronizer accountSynchronizer;

    public ATM() {

        cashbox = new Cashbox();

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
            } else {
                System.out.println("Not enough money");
            }
        }

        return Optional.empty();
    }

    public void depositMoney(final HashMap<Moneynote, Integer> amount) {

        if (loggedInClient.getClient() != null) {
            cashbox.deposit(amount);

            final BigDecimal currentBalance = loggedInClient.getClient().getBankBalance();
            final BigDecimal newBankBalance = currentBalance.add(convertMoneynotesToAmount(amount));
            loggedInClient.getClient().setBankBalance(newBankBalance);

            loggedInClient.persistClient();
        }
    }

    public void transferMoney(String recipientIBAN, BigDecimal amount, String purpose) {

        if (isLoggedInClientsBankBalanceValid(amount)) {

            CashTransfer cashTransfer = new CashTransfer(recipientIBAN, loggedInClient.getClient().getIban(), amount,
                                                         LocalDateTime.now(), purpose);

            accountSynchronizer.sychronizeAccounts(recipientIBAN, amount);

            accountSynchronizer.addCashTransferToClients(cashTransfer);
        } else {

            System.out.println("Not enough money");
        }
    }

    public boolean login(String iban, String pin) {

        if (loggedInClient.logIn(iban, pin)) {

            accountSynchronizer = new AccountSynchronizer(loggedInClient.getClient());
            return true;
        } else {

            return false;
        }
    }

    public boolean logout() {

        accountSynchronizer = null;
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
}
