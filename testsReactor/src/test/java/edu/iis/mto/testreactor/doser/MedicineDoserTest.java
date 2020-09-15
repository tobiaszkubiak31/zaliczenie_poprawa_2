package edu.iis.mto.testreactor.doser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.iis.mto.testreactor.doser.infuser.Infuser;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MedicineDoserTest {

	private static final int AMOUNT_100 = 100;
	private static final String MEDICINE_NAME = "med";
	private static final CapacityUnit MILIMETER_UNIT = CapacityUnit.MILILITER;
	private static final int DOSAGE_COUNT = 1;

	@Mock
	private Infuser infuser;

	@Mock
	private Clock clock;

	@Mock
	private DosageLog dosageLog;
	private MedicineDoser medicineDoser;

	@BeforeEach
	public void setUp() {
		medicineDoser = new MedicineDoser(infuser, dosageLog, clock);
	}

	@Test
	void whenReceiptAndMedicinePackageHaveCommonPropertiesShouldDoseReturnSuccess() {
		Medicine commonMedicine = getMedicine(MEDICINE_NAME);
		Capacity commonCapacity = getCapacity(AMOUNT_100, MILIMETER_UNIT);

		MedicinePackage properMedicinePackage = getMedicinePack(commonMedicine, commonCapacity);
		medicineDoser.add(properMedicinePackage);
		Receipe properReceipe = getReceipe(commonMedicine, commonCapacity, AMOUNT_100, DOSAGE_COUNT);

		DosingResult dosingResult = medicineDoser.dose(properReceipe);

		assertEquals(DosingResult.SUCCESS, dosingResult);
	}

	@Test
	void whenReceiptAndMedicinePackageAreProper_ShouldDosageLogCallDosageLogMethodsProperly() {
		Medicine commonMedicine = getMedicine(MEDICINE_NAME);
		Capacity commonCapacity = getCapacity(AMOUNT_100, MILIMETER_UNIT);

		MedicinePackage properMedicinePackage = getMedicinePack(commonMedicine, commonCapacity);
		medicineDoser.add(properMedicinePackage);
		Receipe properReceipe = getReceipe(commonMedicine, commonCapacity, AMOUNT_100, DOSAGE_COUNT);
		medicineDoser.dose(properReceipe);

		verify(dosageLog).logStartDose(properReceipe.getMedicine(), properReceipe.getDose());
		verify(dosageLog).logEndDose(properReceipe.getMedicine(), properReceipe.getDose());
	}

	@Test
	void whenDosageLogThrowRunTimeException_ShouldDosageLogCallLogErrorMethod() {
		Medicine commonMedicine = getMedicine(MEDICINE_NAME);
		Capacity commonCapacity = getCapacity(AMOUNT_100, MILIMETER_UNIT);

		MedicinePackage properMedicinePackage = getMedicinePack(commonMedicine, commonCapacity);
		medicineDoser.add(properMedicinePackage);
		Receipe properReceipe = getReceipe(commonMedicine, commonCapacity, AMOUNT_100, DOSAGE_COUNT);

		RuntimeException expectedException = new RuntimeException();
		doThrow(expectedException).when(dosageLog).logStartDose(any(), any());
		medicineDoser.dose(properReceipe);

		verify(dosageLog).logError(properReceipe, expectedException.getMessage());
	}


	@Test
	void whenTooHighDosageInReceipt_ShouldThrowInsufficientMedicineException() {
		final int TOO_HIGH_DOSAGE = 2;
		Medicine commonMedicine = getMedicine(MEDICINE_NAME);
		Capacity commonCapacity = getCapacity(AMOUNT_100, MILIMETER_UNIT);

		MedicinePackage properMedicinePackage = getMedicinePack(commonMedicine, commonCapacity);
		medicineDoser.add(properMedicinePackage);
		Receipe properReceipe = getReceipe(commonMedicine, commonCapacity, AMOUNT_100, TOO_HIGH_DOSAGE);

		Assertions.assertThrows(InsufficientMedicineException.class,
			() -> medicineDoser.dose(properReceipe));
	}


	private MedicinePackage getMedicinePack(Medicine medicine, Capacity capacity) {
		return MedicinePackage.of(medicine, capacity);
	}

	private Receipe getReceipe(Medicine medicine, Capacity capacity, int amount100, int i) {
		return Receipe
			.of(medicine, Dose.of(capacity, Period.of(amount100, TimeUnit.MILLISECONDS)), i);
	}

	private Medicine getMedicine(String medicineName) {
		return Medicine.of(medicineName);
	}

	private Capacity getCapacity(int amount100, CapacityUnit milimeterUnit) {
		return Capacity.of(amount100, milimeterUnit);
	}


}
