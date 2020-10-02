/*
 * Currency.java
 *
 * Created on 2020-10-02
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package currency_v2;

public class Currency {

    private CurrencyID currencyID;

    public Currency(final CurrencyID currencyID) {
        this.currencyID = currencyID;
    }

    /*
    public static List<Integer> getAllPossibleMoneyNotes(final currency_v2.Currency currency) {

        ArrayList<Integer> possibleMoneyNotes = null;

        switch (currency) {

            case EURO:
                possibleMoneyNotes = Arrays.stream(Euro.values()).map(Euro::getValue).collect(
                        Collectors.toCollection(ArrayList::new));
        }

        return possibleMoneyNotes;
    }

     */

}
