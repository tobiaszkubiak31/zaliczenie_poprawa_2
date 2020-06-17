package edu.iis.mto.testreactor.atm;

import static java.util.Objects.requireNonNull;

import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MoneyDeposit {

    private final Currency currency;
    private final Map<Banknote, BanknotesPack> deposit;

    public static MoneyDeposit create(Currency currency, List<BanknotesPack> deposit) {
        return new MoneyDeposit(requireNonNull(currency), requireNonNull(deposit));
    }

    private MoneyDeposit(Currency currency, List<BanknotesPack> deposit) {
        this.currency = currency;
        this.deposit = deposit.stream()
                              .collect(Collectors.toMap(BanknotesPack::getDenomination, Function.identity(), (pack1,
                                      pack2) -> BanknotesPack.create(pack1.getCount() + pack2.getCount(), pack1.getDenomination())));
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
        return deposit.getOrDefault(note, BanknotesPack.create(0, note));
    }

    public void release(BanknotesPack banknotesPack) {
        Banknote denomination = banknotesPack.getDenomination();
        BanknotesPack existing = getFor(denomination);
        int newCount = existing.getCount() - banknotesPack.getCount();
        if (newCount < 0) {
            throw new IllegalArgumentException("unavailable banknotes count " + banknotesPack.getCount());
        }
        this.deposit.put(denomination, BanknotesPack.create(newCount, existing.getDenomination()));
    }

    public List<BanknotesPack> getBanknotes() {
        return deposit.values()
                      .stream()
                      .collect(Collectors.toList());
    }

}
