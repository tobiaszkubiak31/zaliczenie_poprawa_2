package edu.iis.mto.testreactor.doser;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class Receipe {

    private final Medicine medicine;
    private final Dose dose;
    private final int number;

    public static Receipe of(Medicine medicine, Dose dose, int number) {
        return new Receipe(requireNonNull(medicine, "medicine == null"), requireNonNull(dose, "dose == null"), number);
    }

    private Receipe(Medicine medicine, Dose dose, int number) {
        this.medicine = medicine;
        this.dose = dose;
        this.number = number;

    }

    public Medicine getMedicine() {
        return medicine;
    }

    public Dose getDose() {
        return dose;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dose, medicine, number);
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
        Receipe other = (Receipe) obj;
        return Objects.equals(dose, other.dose) && Objects.equals(medicine, other.medicine) && number == other.number;
    }

    @Override
    public String toString() {
        return "Receipe [medicine=" + medicine + ", dose=" + dose + ", number=" + number + "]";
    }

}
