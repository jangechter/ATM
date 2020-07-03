/*
 * AccountSynchronisationException.java
 *
 * Created on 2020-07-03
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package Exceptions;

public class AccountSynchronisationException extends RuntimeException {

    public AccountSynchronisationException(final String message) {
        super(message);
    }
}
