package edu.iis.mto.testreactor.doser;

public class InsufficientMedicineException extends MedicineException {

    private static final long serialVersionUID = 1L;

    public InsufficientMedicineException(Medicine medicine) {
        super(medicine);
    }

}
