/*
 * Cashbox.java
 *
 * Created on 2020-04-20
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package cashbox;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import Exceptions.WithdrawNotPossibleException;
import csvWriter.CSVWriter;
import moneynote.Moneynote;

public class Cashbox {

    private HashMap<Moneynote, Integer> notes;
    public static final List<Moneynote> POSSIBLE_NOTES = new LinkedList<>();

    public Cashbox(final HashMap<Moneynote, Integer> notes) {
        this.notes = notes;
    }

    //add the deposit cash into the cashbox
    public void deposit(final HashMap<Moneynote, Integer> depositMoneynotes) {

        depositMoneynotes.forEach((moneynote, integer) -> {

            //to avoid nullpointer
            if (notes.containsKey(moneynote)) {

                notes.put(moneynote, notes.get(moneynote) + depositMoneynotes.get(moneynote));
            } else {
                notes.put(moneynote, depositMoneynotes.get(moneynote));
            }
        });
    }

    //return the amount in a map of moneynotes
    public HashMap<Moneynote, Integer> withdraw(final Integer amount) {

        final HashMap<Moneynote, Integer> withdrawMoneynotes;

        if (isWithdrawPossible(amount)) {

            return calculateHashMap(amount);
        } else {
            throw new IllegalArgumentException("invalid value");
        }
    }

    //return the next lower available
    private Moneynote getNextLowerMoneynote(int amount) {

        amount--;

        final Set<Moneynote> moneynotes = notes.keySet().stream().sorted(Collections.reverseOrder()).collect(
                Collectors.toCollection(LinkedHashSet::new));

        //find the next lower moneynote in cashbox
        for (Moneynote m : moneynotes) {
            if ((m.getValue() < amount) && (notes.get(m) > 0)) {
                return m;
            }
        }

        return getLowestavAilableMoneynoteInCashbox();
    }

    //return the lowest available moneynote
    private Moneynote getLowestavAilableMoneynoteInCashbox() {

        return Collections.min(notes.keySet());
    }

    //check if the withdraw with this amount and the current currency is possible
    private boolean isWithdrawPossible(Integer amount) {

        return ((amount % getLowestavAilableMoneynoteInCashbox().getValue()) == 0) && (amount != 0);
    }

    private Moneynote getMiddleMoneynotes() {

        if (notes.size() >= 3) {
            if ((notes.size() % 2) != 0) {

                return notes.keySet().stream().sorted().collect(Collectors.toList()).get(((notes.size() + 1) / 2) - 1);
            } else {
                List<Moneynote> list = notes.keySet().stream().sorted().collect(Collectors.toList());

                return new Moneynote(Math.round((list.get((notes.size() / 2) - 1).getValue() + list.get(
                        (notes.size() / 2)).getValue()) / 2));
            }
        }

        return getLowestavAilableMoneynoteInCashbox();
    }

    private void persistCashbox() {

        try {
            CSVWriter.writeCashbox(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //calculate the map
    private HashMap<Moneynote, Integer> calculateHashMap(Integer amount) {
        final HashMap<Moneynote, Integer> withdrawMoneynotes
                = new HashMap<>();                                  //map which contains the moneynotes to withdraw
        Moneynote nextLowerMoneynote;                                           //temp moneynote for the calculation

        while (amount > 0) {

            if ((notes.getOrDefault(new Moneynote(amount), 0) != 0) && (amount <= getMiddleMoneynotes().getValue())) {

                syncHashMaps(new Moneynote(amount), withdrawMoneynotes);

                amount = 0;
            } else {

                nextLowerMoneynote = getNextLowerMoneynote(amount);

                syncHashMaps(nextLowerMoneynote, withdrawMoneynotes);

                amount -= nextLowerMoneynote.getValue();
            }
        }

        persistCashbox();

        return withdrawMoneynotes;
    }

    //synchronize notes with the notes which will withdraw
    private void syncHashMaps(final Moneynote moneynote, final HashMap<Moneynote, Integer> withdrawMoneynotes)
            throws WithdrawNotPossibleException {

        //put moneynote in withdraw map
        if (withdrawMoneynotes.containsKey(moneynote)) {
            withdrawMoneynotes.put(moneynote, withdrawMoneynotes.get(moneynote) + 1);
        } else {
            withdrawMoneynotes.put(moneynote, 1);
        }

        //take moneynote out of notes map
        if ((notes.get(moneynote) - 1) >= 0) {
            notes.put(moneynote, notes.get(moneynote) - 1);
        } else {
            //if there are no notes anymore
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
