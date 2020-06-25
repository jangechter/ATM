/*
 * Client.java
 *
 * Created on 2020-06-25
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package client;

import java.math.BigDecimal;
import java.util.Objects;

import repositories.CashTransferRepository;

public class Client {

    private final java.lang.String name;
    private final java.lang.String firstName;
    private final java.lang.String iban;
    private java.lang.String pin;
    private BigDecimal bankBalance;
    private boolean isActive;
    private Integer numberAttempts;
    private CashTransferRepository clientRepository;

    public Client(final java.lang.String name, final java.lang.String firstName, final java.lang.String iban,
                  final java.lang.String pin,
                  final BigDecimal bankBalance, final boolean isActive, final Integer numberAttempts) {

        this.name = name;
        this.firstName = firstName;
        this.iban = iban;
        this.pin = pin;
        this.bankBalance = bankBalance;
        this.isActive = isActive;
        this.numberAttempts = numberAttempts;
        clientRepository = new CashTransferRepository(this);
    }

    public CashTransferRepository getClientRepository() {
        return clientRepository;
    }

    public java.lang.String getName() {
        return name;
    }

    public java.lang.String getFirstName() {
        return firstName;
    }

    public java.lang.String getIban() {
        return iban;
    }

    public java.lang.String getPin() {
        return pin;
    }

    public BigDecimal getBankBalance() {
        return bankBalance;
    }

    public boolean isActive() {
        return isActive;
    }

    public Integer getNumberAttempts() {
        return numberAttempts;
    }

    public void setPin(final java.lang.String pin) {
        this.pin = pin;
    }

    public void setBankBalance(final BigDecimal bankBalance) {
        this.bankBalance = bankBalance;
    }

    public void setActive(final boolean active) {
        isActive = active;
    }

    public void setNumberAttempts(final Integer numberAttempts) {
        this.numberAttempts = numberAttempts;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        final Client client = (Client) obj;
        return (isActive == client.isActive) &&
               name.equals(client.name) &&
               firstName.equals(client.firstName) &&
               iban.equals(client.iban) &&
               pin.equals(client.pin) &&
               bankBalance.equals(client.bankBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, firstName, iban, pin, bankBalance, isActive);
    }
}
