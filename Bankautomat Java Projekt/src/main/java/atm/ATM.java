/*
 * ATM.java
 *
 * Created on 2020-10-02
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package atm;

import Exceptions.AccountSynchronisationException;
import Exceptions.WithdrawNotPossibleException;
import accountSynchronizer.AccountSynchronizer;
import authentication.Authentication;
import cashTransfer.CashTransfer;
import cashbox.Cashbox;
import currency.Currency;
import moneynote.Moneynote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;

public class ATM {

    private final Authentication loggedInClient = new Authentication();
    private final Cashbox cashbox;
    private final currency.Currency currency = Currency.EURO;
    private AccountSynchronizer accountSynchronizer;

    public ATM() {

        cashbox = new Cashbox();

        final Properties properties = new Properties();

        try (BufferedReader r = new BufferedReader(
                new FileReader(new File(System.getProperty("user.dir") + "/properties/properties.env")))) {

            properties.load(r);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public Optional<HashMap<Moneynote, Integer>> withdrawMoney(final Integer amount) throws
            WithdrawNotPossibleException {

        final HashMap<Moneynote, Integer> withdrawNotes;

        if (loggedInClient.getClient() == null) {

            return Optional.empty();
        }

        if (isLoggedInClientsBankBalanceInvalid(new BigDecimal(amount))) {

            System.out.println("Not enough money");
            throw new WithdrawNotPossibleException("Not enough money");
        }

        withdrawNotes = cashbox.withdraw(amount);

        loggedInClient.getClient().setBankBalance(
                loggedInClient.getClient().getBankBalance()
                              .subtract(BigDecimal.valueOf(amount)));

        final CashTransfer withdrawCT = new CashTransfer("ATM " + Locale.getDefault().getCountry(),
                                                         loggedInClient.getClient().getIban(),
                                                         BigDecimal.valueOf(amount).negate(), LocalDateTime.now(),
                                                         String.format("ATM withdraw: %d", amount));

        loggedInClient.getClient().getCashRepository().addCashTransfer(withdrawCT);

        loggedInClient.persistClient();

        return Optional.of(withdrawNotes);
    }

    public void depositMoney(final HashMap<Moneynote, Integer> amountMoneyNoteMap)
            throws AccountSynchronisationException {

        if (loggedInClient.getClient() == null) {
            return;
        }
        cashbox.deposit(amountMoneyNoteMap);

        final BigDecimal amount = Cashbox.convertMoneynotesToAmount(amountMoneyNoteMap);

        final BigDecimal currentBalance = loggedInClient.getClient().getBankBalance();
        final BigDecimal newBankBalance = currentBalance.add(amount);
        loggedInClient.getClient().setBankBalance(newBankBalance);

        final CashTransfer depositCT = new CashTransfer(getLoggedInClient().getClient().getIban(),
                                                        "ATM " + Locale.getDefault().getCountry(), amount,
                                                        LocalDateTime.now(),
                                                        String.format("ATM deposit: %d", amount.intValue()));

        loggedInClient.getClient().getCashRepository().addCashTransfer(depositCT);

        loggedInClient.persistClient();
    }

    public void transferMoney(final String recipientIBAN, final BigDecimal amount, final String purpose) {

        if (isLoggedInClientsBankBalanceInvalid(amount)) {

            throw new AccountSynchronisationException("Not enough money");
        }

        final CashTransfer repCashTransfer = new CashTransfer(recipientIBAN, loggedInClient.getClient().getIban(),
                                                              amount,
                                                              LocalDateTime.now(), purpose);

        final CashTransfer appCashTransfer = new CashTransfer(recipientIBAN, loggedInClient.getClient().getIban(),
                                                              amount.negate(),
                                                              LocalDateTime.now(), purpose);

        accountSynchronizer.sychronizeAccounts(recipientIBAN, amount);
        accountSynchronizer.addCashTransferToClients(repCashTransfer, appCashTransfer);
    }

    public boolean login(final String iban, final String pin) {

        if (loggedInClient.logIn(iban, pin)) {

            accountSynchronizer = new AccountSynchronizer(loggedInClient.getClient());
            return true;
        }

        return false;
    }

    public boolean logout() {

        accountSynchronizer = null;
        return loggedInClient.logout();
    }

    public Authentication getLoggedInClient() {
        return loggedInClient;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Cashbox getCashbox() {
        return cashbox;
    }

    private boolean isLoggedInClientsBankBalanceInvalid(final BigDecimal amount) {

        return (loggedInClient.getClient().getBankBalance().doubleValue() - amount.doubleValue()) < 0;
    }
}
