package edu.iis.mto.testreactor.doser;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Period {

    private final int amount;
    private final TimeUnit units;

    private Period(int amount, TimeUnit units) {
        this.amount = amount;
        this.units = units;
    }

    public static Period of(int amount, TimeUnit units) {
        return new Period(amount, units);
    }

    public int getAmount() {
        return amount;
    }

    public TimeUnit getUnits() {
        return units;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, units);
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
        Period other = (Period) obj;
        return amount == other.amount && units == other.units;
    }

    @Override
    public String toString() {
        return "Per [amount=" + amount + ", units=" + units + "]";
    }

}
