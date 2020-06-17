package edu.iis.mto.testreactor.atm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * a pack of the same banknotes
 *
 */
public class BanknotesPack {

    private final AtomicInteger count;
    private final Banknote denomination;

    public static BanknotesPack create(int count, Banknote note) {
        return new BanknotesPack(note, count);
    }

    private BanknotesPack(Banknote note, int count) {
        this.count = new AtomicInteger(count);
        this.denomination = note;
    }

    public int getCount() {
        return count.get();
    }

    public Banknote getDenomination() {
        return denomination;
    }

}
