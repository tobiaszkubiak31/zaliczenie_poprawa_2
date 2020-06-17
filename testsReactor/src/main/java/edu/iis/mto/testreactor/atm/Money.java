package edu.iis.mto.testreactor.atm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

public class Money {

    public static final Currency DEFAULT_CURRENCY = Currency.getInstance("EUR");

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    private BigDecimal denomination;

    private String currencyCode;

    protected Money() {}

    public Money(int denomination, Currency currency) {
        this(new BigDecimal(denomination), currency.getCurrencyCode());
    }

    public Money(BigDecimal denomination, Currency currency) {
        this(denomination, currency.getCurrencyCode());
    }

    private Money(BigDecimal denomination, String currencyCode) {
        this.denomination = denomination.setScale(2, RoundingMode.HALF_EVEN);
        this.currencyCode = currencyCode;
    }

    public Money(BigDecimal denomination) {
        this(denomination, DEFAULT_CURRENCY);
    }

    public Money(double denomination, Currency currency) {
        this(new BigDecimal(denomination), currency.getCurrencyCode());
    }

    public Money(double denomination, String currencyCode) {
        this(new BigDecimal(denomination), currencyCode);
    }

    public Money(double denomination) {
        this(denomination, DEFAULT_CURRENCY);
    }

    public Money multiplyBy(double multiplier) {
        return multiplyBy(new BigDecimal(multiplier));
    }

    public Money multiplyBy(BigDecimal multiplier) {
        return new Money(denomination.multiply(multiplier), currencyCode);
    }

    public Money add(Money money) {
        if (!compatibleCurrency(money)) {
            throw new IllegalArgumentException("Currency mismatch");
        }

        return new Money(denomination.add(money.denomination), determineCurrencyCode(money));
    }

    public Money subtract(Money money) {
        if (!compatibleCurrency(money)) {
            throw new IllegalArgumentException("Currency mismatch");
        }

        return new Money(denomination.subtract(money.denomination), determineCurrencyCode(money));
    }

    private boolean compatibleCurrency(Money money) {
        return isZero(denomination) || isZero(money.denomination) || currencyCode.equals(money.getCurrencyCode());
    }

    private boolean isZero(BigDecimal testedValue) {
        return BigDecimal.ZERO.compareTo(testedValue) == 0;
    }

    private Currency determineCurrencyCode(Money otherMoney) {
        String resultingCurrenctCode = isZero(denomination) ? otherMoney.currencyCode : currencyCode;
        return Currency.getInstance(resultingCurrenctCode);
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Currency getCurrency() {
        return Currency.getInstance(currencyCode);
    }

    public boolean greaterThan(Money other) {
        return denomination.compareTo(other.denomination) > 0;
    }

    public boolean lessThan(Money other) {
        return denomination.compareTo(other.denomination) < 0;
    }

    public boolean lessOrEquals(Money other) {
        return denomination.compareTo(other.denomination) <= 0;
    }

    @Override
    public String toString() {
        return String.format("%0$.2f %s", denomination, getCurrency().getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyCode, denomination);
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
        Money other = (Money) obj;
        return Objects.equals(currencyCode, other.currencyCode) && Objects.equals(denomination, other.denomination);
    }

}
