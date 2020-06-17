package edu.iis.mto.testreactor.atm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * a pack of the same banknotes
 *
 */
public class BanknotesPack {

    private final AtomicInteger count;
    private final Banknote denomination;

    private BanknotesPack(Builder builder) {
        this.count = new AtomicInteger(builder.count);
        this.denomination = builder.denomination;
    }

    public int getCount() {
        return count.get();
    }

    public Banknote getDenomination() {
        return denomination;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private int count;
        private Banknote denomination;

        private Builder() {}

        public Builder withCount(int count) {
            this.count = count;
            return this;
        }

        public Builder withDenomination(Banknote denomination) {
            this.denomination = denomination;
            return this;
        }

        public BanknotesPack build() {
            return new BanknotesPack(this);
        }
    }

}
