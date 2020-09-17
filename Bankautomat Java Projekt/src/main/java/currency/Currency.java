/*
 * Currency.java
 *
 * Created on 2020-09-17
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package currency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Currency {

    EURO,
    DOLLAR;

    public static List<Integer> getAllPossibleMoneyNotes(Currency currency) {

        ArrayList<Integer> possibleMoneyNotes = null;

        switch (currency) {

            case EURO:
                possibleMoneyNotes = Arrays.stream(Euro.values()).map(Euro::getValue).collect(
                        Collectors.toCollection(ArrayList::new));
        }

        return possibleMoneyNotes;
    }
}
