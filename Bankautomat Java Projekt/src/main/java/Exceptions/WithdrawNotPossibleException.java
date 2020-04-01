/*
 * WithdrawNotPossibleException.java
 *
 * Created on 2020-04-01
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package Exceptions;

public class WithdrawNotPossibleException extends RuntimeException {
    public WithdrawNotPossibleException(final String message) {
        super(message);
    }
}
