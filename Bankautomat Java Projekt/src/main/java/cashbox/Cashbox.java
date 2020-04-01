/*
 * Cashbox.java
 *
 * Created on 2020-04-01
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package cashbox;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import Exceptions.WithdrawNotPossibleException;
import moneynote.Moneynote;

public class Cashbox {

    private HashMap<Moneynote, Integer> notes;
    public static final List<Moneynote> POSSIBLE_NOTES = new LinkedList<>();

    public Cashbox(final HashMap<Moneynote, Integer> notes) {
        this.notes = notes;

        POSSIBLE_NOTES.add(new Moneynote(500));
        POSSIBLE_NOTES.add(new Moneynote(200));
        POSSIBLE_NOTES.add(new Moneynote(100));
        POSSIBLE_NOTES.add(new Moneynote(50));
        POSSIBLE_NOTES.add(new Moneynote(20));
        POSSIBLE_NOTES.add(new Moneynote(10));
        POSSIBLE_NOTES.add(new Moneynote(5));
        POSSIBLE_NOTES.add(new Moneynote(2));
        POSSIBLE_NOTES.add(new Moneynote(1));
    }

    public HashMap<Moneynote, Integer> withdraw(final Integer amount) {

        final HashMap<Moneynote, Integer> withdrawMoneynotes;

        if (isWithdrawPossible(amount)) {

            return calculateHashMap(amount);
        } else {
            throw new IllegalArgumentException("invalid value");
        }
    }

    private Moneynote getNextMoneynote(int amount) {

        amount--;

        for (Moneynote m : POSSIBLE_NOTES) {

            if ((m.getValue() < amount) && notes.containsKey(m) && (notes.get(m) > 0)) {
                return m;
            }
        }

        return getLowestAvaiableMoneynoteInCashbox();
    }

    private Moneynote getLowestAvaiableMoneynoteInCashbox() {

        return Collections.min(notes.keySet());
    }

    private boolean isWithdrawPossible(Integer amount) {

        return ((amount % getLowestAvaiableMoneynoteInCashbox().getValue()) == 0) && (amount != 0);
    }

    private HashMap<Moneynote, Integer> calculateHashMap(Integer amount) {
        final HashMap<Moneynote, Integer> withdrawMoneynotes = new HashMap<>();
        Moneynote nextLowerMoneynote;

        for (int i = 0; i < 30; i++) {

            if (amount <= 0) {
                break;
            }

            if (notes.containsKey(new Moneynote(amount)) && (amount <= 50) && (notes.get(new Moneynote(amount)) > 0)) {

                syncHashMaps(new Moneynote(amount), withdrawMoneynotes);

                amount = 0;
            } else {

                nextLowerMoneynote = getNextMoneynote(amount);

                syncHashMaps(nextLowerMoneynote, withdrawMoneynotes);

                amount -= nextLowerMoneynote.getValue();
            }
        }

        return withdrawMoneynotes;
    }

    private void syncHashMaps(final Moneynote moneynote, final HashMap<Moneynote, Integer> withdrawMoneynotes) {

        if (withdrawMoneynotes.containsKey(moneynote)) {
            withdrawMoneynotes.put(moneynote, withdrawMoneynotes.get(moneynote) + 1);
        } else {
            withdrawMoneynotes.put(moneynote, 1);
        }

        if ((notes.get(moneynote) - 1) >= 0) {
            notes.put(moneynote, notes.get(moneynote) - 1);
        } else {
            throw new WithdrawNotPossibleException("Not enough moneynotes");
        }
    }

    public HashMap<Moneynote, Integer> getNotes() {
        return notes;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Cashbox cashbox = (Cashbox) o;
        return notes.equals(cashbox.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notes);
    }
}
