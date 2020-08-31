package edu.iis.mto.testreactor.doser;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class Capacity {

    private final int amount;
    private final CapacityUnit unit;

    public static Capacity of(int amount, CapacityUnit unit) {
        return new Capacity(amount, requireNonNull(unit, "unit == null"));
    }

    private Capacity(int amount, CapacityUnit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public int getAmount() {
        return amount;
    }

    public CapacityUnit getUnit() {
        return unit;
    }

    public Capacity times(int number) {

        if (number < 1) {
            throw new IllegalArgumentException("must be > 0, found " + number);
        }
        return Capacity.of(amount * number, unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, unit);
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
        Capacity other = (Capacity) obj;
        return amount == other.amount && unit == other.unit;
    }

    public boolean greaterThan(Capacity capacity) {
        return (long) this.amount * unit.getValue() > (long) capacity.amount * capacity.unit.getValue();
    }

}
