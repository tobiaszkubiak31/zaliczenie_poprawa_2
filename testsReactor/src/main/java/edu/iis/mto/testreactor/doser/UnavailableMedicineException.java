package edu.iis.mto.testreactor.doser;

public class UnavailableMedicineException extends MedicineException {

    private static final long serialVersionUID = 1L;

    public UnavailableMedicineException(Medicine medicine) {
        super(medicine);
    }

}
