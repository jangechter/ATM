/*
 * Client.java
 *
 * Created on 2020-03-13
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package client;

import java.math.BigDecimal;
import java.util.Objects;

public class Client {

    private String name;
    private String firstname;
    private String iban;
    private String pin;
    private BigDecimal bankBalance;
    private boolean isActive;

    public Client(final String name, final String firstname, final String iban, final String pin,
                  final BigDecimal bankBalance, final boolean isActive) {
        this.name = name;
        this.firstname = firstname;
        this.iban = iban;
        this.pin = pin;
        this.bankBalance = bankBalance;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getIban() {
        return iban;
    }

    public String getPin() {
        return pin;
    }

    public BigDecimal getBankBalance() {
        return bankBalance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setPin(final String pin) {
        this.pin = pin;
    }

    public void setBankBalance(final BigDecimal bankBalance) {
        this.bankBalance = bankBalance;
    }

    public void setActive(final boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        final Client client = (Client) o;
        return Objects.equals(name, client.name) &&
               Objects.equals(firstname, client.firstname) &&
               Objects.equals(iban, client.iban) &&
               Objects.equals(pin, client.pin) &&
               Objects.equals(bankBalance, client.bankBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, firstname, iban, pin, bankBalance);
    }
}
