/*
 * ClientParsingException.java
 *
 * Created on 2020-04-03
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package Exceptions;

import java.io.IOException;

public class ClientParsingException extends IOException {

    public ClientParsingException(final String message) {
        super(message);
    }
}
