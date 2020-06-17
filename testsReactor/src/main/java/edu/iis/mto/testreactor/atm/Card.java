package edu.iis.mto.testreactor.atm;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class Card {

    private final String number;

    public static Card create(String number) {
        return new Card(requireNonNull(number));
    }

    private Card(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Card other = (Card) obj;
        return Objects.equals(number, other.number);
    }

    @Override
    public String toString() {
        return "Card [number=" + number + "]";
    }

}
