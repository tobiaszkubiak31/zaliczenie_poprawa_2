package edu.iis.mto.testreactor.atm;

import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum Banknote {

    EU_5(5, "EU"),
    EU_10(10, "EU"),
    EU_20(20, "EU"),
    EU_50(50, "EU"),
    EU_100(100, "EU"),
    EU_200(200, "EU"),
    PL_10(10, "PLN"),
    PL_20(20, "PLN"),
    PL_50(50, "PLN"),
    PL_100(100, "PLN"),
    PL_200(200, "PLN"),
    PL_500(200, "PLN");

    private static final Map<Currency, List<Banknote>> banknotesForCurrency;
    static {
        banknotesForCurrency = new HashMap<>();
        banknotesForCurrency.put(Currency.getInstance("EU"), List.of(EU_5, EU_10, EU_20, EU_50, EU_100, EU_200));
        banknotesForCurrency.put(Currency.getInstance("PLN"), List.of(PL_10, PL_20, PL_50, PL_100, PL_200, PL_500));
    }
    private final int denomination;
    private final Currency currency;

    private Banknote(int denomination, String currencyCode) {
        this.denomination = denomination;
        this.currency = Currency.getInstance(currencyCode);
    }

    public int getDenomination() {
        return denomination;
    }

    public Currency getCurrency() {
        return currency;
    }

    public static List<Banknote> getAscFor(Currency currency) {
        return banknotesForCurrency.getOrDefault(currency, List.of());
    }

    public static List<Banknote> getDescFor(Currency currency) {
        List<Banknote> banknotes = getAscFor(currency);
        return IntStream.range(banknotes.size() - 1, -1)
                        .mapToObj(banknotes::get)
                        .collect(Collectors.toList());
    }

}
