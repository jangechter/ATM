/*
 * Cashbox.java
 *
 * Created on 2020-03-25
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package cashbox;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import moneynote.Moneynote;

public class Cashbox {

    private HashMap<Moneynote, Integer> notes;
    public final List<Moneynote> possibleNotes = new LinkedList<>();

    public Cashbox(final HashMap<Moneynote, Integer> notes) {
        this.notes = notes;

        possibleNotes.add(new Moneynote(200));
        possibleNotes.add(new Moneynote(100));
        possibleNotes.add(new Moneynote(50));
        possibleNotes.add(new Moneynote(20));
        possibleNotes.add(new Moneynote(10));
        possibleNotes.add(new Moneynote(5));
    }

    public HashMap<Moneynote, Integer> withdraw(final Integer amount) throws IllegalArgumentException {

        return calculateDenominations(amount);
    }

    private Moneynote getNextMoneynote(Integer ammount) {

        if (possibleNotes.contains(new Moneynote(ammount))) {
            return possibleNotes.get(possibleNotes.indexOf(new Moneynote(ammount)));
        }

        for (Moneynote m : possibleNotes) {

            if (m.getValue() < ammount) {
                return m;
            }
        }

        return null;
    }

    private HashMap<Moneynote, Integer> calculateDenominations(Integer amount) {

        HashMap<Moneynote, Integer> withdrawMoneynotes = new HashMap<>();
        Moneynote nextLowerMoneynote;
        Moneynote moneynoteEvenAmount;

        for (int i = 0; i < 20; i++) {

            if (amount <= 0) {
                break;
            }

            if (notes.containsKey(new Moneynote(amount)) && (amount < 50)) {

                moneynoteEvenAmount = new Moneynote(amount);
                withdrawMoneynotes.put(moneynoteEvenAmount, 1);

                notes.put(new Moneynote(amount), notes.get(moneynoteEvenAmount) - 1);

                amount = 0;
            } else {
                amount--;

                nextLowerMoneynote = getNextMoneynote(amount);
                if (notes.containsKey(nextLowerMoneynote)) {

                    if (withdrawMoneynotes.containsKey(nextLowerMoneynote)) {
                        withdrawMoneynotes.put(nextLowerMoneynote, withdrawMoneynotes.get(nextLowerMoneynote) + 1);
                    } else {
                        withdrawMoneynotes.put(nextLowerMoneynote, 1);
                    }

                    notes.put(nextLowerMoneynote, notes.get(nextLowerMoneynote) - 1);

                    if (amount % 5 != 0) {
                        amount++;
                    }

                    amount -= nextLowerMoneynote.getValue();
                }
            }
        }

        return withdrawMoneynotes;
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
