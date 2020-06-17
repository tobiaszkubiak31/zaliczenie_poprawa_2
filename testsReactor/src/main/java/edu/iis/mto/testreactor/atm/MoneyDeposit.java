package edu.iis.mto.testreactor.atm;

import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MoneyDeposit {

    private final Currency currency;
    private final Map<Banknote, BanknotesPack> deposit;

    private MoneyDeposit(Builder builder) {
        this.currency = builder.currency;
        this.deposit = builder.deposit.stream()
                                      .collect(Collectors.toMap(BanknotesPack::getDenomination, Function.identity()));
    }

    public Currency getCurrency() {
        return currency;
    }

    public boolean isAvailable(Banknote note, int amount) {
        return getFor(note).getCount() > amount;
    }

    public boolean isAvailable(BanknotesPack banknotes) {
        return isAvailable(banknotes.getDenomination(), banknotes.getCount());
    }

    public int getAvailableCountOf(Banknote banknote) {
        return getFor(banknote).getCount();
    }

    private BanknotesPack getFor(Banknote note) {
        return deposit.getOrDefault(note, BanknotesPack.builder()
                                                       .withCount(0)
                                                       .withDenomination(note)
                                                       .build());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private Currency currency;
        private List<BanknotesPack> deposit = Collections.emptyList();

        private Builder() {}

        public Builder withCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder withDeposit(List<BanknotesPack> deposit) {
            this.deposit = deposit;
            return this;
        }

        public MoneyDeposit build() {
            return new MoneyDeposit(this);
        }
    }

}
