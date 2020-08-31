package edu.iis.mto.testreactor.doser;

public class MedicineException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private Medicine medicine;

    public MedicineException(Medicine medicine) {
        super(medicine.toString());
        this.medicine = medicine;
    }

    public Medicine getMedicine() {
        return medicine;
    }
}
