package edu.iis.mto.testreactor.doser.infuser;

import edu.iis.mto.testreactor.doser.Capacity;
import edu.iis.mto.testreactor.doser.MedicinePackage;

public interface Infuser {

    void dispense(MedicinePackage medicinePackage, Capacity capacity) throws InfuserException;

}
