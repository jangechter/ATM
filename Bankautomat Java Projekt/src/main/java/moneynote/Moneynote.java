/*
 * Moneynote.java
 *
 * Created on 2020-03-25
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package moneynote;

import java.util.Objects;

public class Moneynote implements Comparable<Moneynote> {

    private Integer value;

    public Moneynote(Integer value) {

        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Moneynote moneynote = (Moneynote) o;
        return value.equals(moneynote.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(final Moneynote o) {

        if(o.getValue().equals(value)){
            return 0;
        }else{
            if(value > 0){
                return 1;
            }else{
                return -1;
            }
        }


    }
}
