package edu.iis.mto.testreactor.atm;

import java.util.Objects;

/**
 * a pack of the same banknotes
 *
 */
public class BanknotesPack {

    private final int count;
    private final Banknote denomination;

    public static BanknotesPack create(int count, Banknote note) {
        return new BanknotesPack(note, count);
    }

    private BanknotesPack(Banknote note, int count) {
        this.count = count;
        this.denomination = note;
    }

    public int getCount() {
        return count;
    }

    public Banknote getDenomination() {
        return denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, denomination);
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
        BanknotesPack other = (BanknotesPack) obj;
        return Objects.equals(count, other.count) && denomination == other.denomination;
    }

    @Override
    public String toString() {
        return "BanknotesPack [count=" + count + ", denomination=" + denomination + "]";
    }

}
