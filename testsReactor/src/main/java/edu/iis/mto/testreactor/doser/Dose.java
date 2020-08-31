package edu.iis.mto.testreactor.doser;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class Dose {

    private final Capacity capacity;
    private final Period period;

    public static Dose of(Capacity capacity, Period per) {
        return new Dose(requireNonNull(capacity, "capacity == null"), requireNonNull(per, "per == null"));
    }

    private Dose(Capacity capacity, Period per) {
        this.capacity = capacity;
        this.period = per;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public Period getPeriod() {
        return period;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, period);
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
        Dose other = (Dose) obj;
        return Objects.equals(capacity, other.capacity) && period == other.period;
    }

}
