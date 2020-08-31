package edu.iis.mto.testreactor.doser;

import java.util.Objects;

public class MedicinePackage {

    private final Medicine medicine;
    private final Capacity capacity;

    public static MedicinePackage of(Medicine medicine, Capacity capacity) {
        return new MedicinePackage(medicine, capacity);
    }

    private MedicinePackage(Medicine medicine, Capacity capacity) {
        this.medicine = medicine;
        this.capacity = capacity;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, medicine);
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
        MedicinePackage other = (MedicinePackage) obj;
        return Objects.equals(capacity, other.capacity) && Objects.equals(medicine, other.medicine);
    }

    @Override
    public String toString() {
        return "MedicinePackage [medicine=" + medicine + ", capacity=" + capacity + "]";
    }

}
