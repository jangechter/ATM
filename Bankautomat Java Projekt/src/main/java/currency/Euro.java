/*
 * Euro.java
 *
 * Created on 2020-09-17
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package currency;

public enum Euro {

    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500);

    private final Integer value;

    Euro(final Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
