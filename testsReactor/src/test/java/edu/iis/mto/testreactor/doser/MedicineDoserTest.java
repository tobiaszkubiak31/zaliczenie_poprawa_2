package edu.iis.mto.testreactor.doser;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

	private final Map<Medicine, MedicinePackage> medicinesTray = new HashMap<>();
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
	void whenReceiptAndMedicinePackageAreProperShouldDoseReturnSuccess() {
		String MedicineName = "med";
		int amount = 100;
		Medicine medicine = Medicine.of(MedicineName);
		CapacityUnit units = CapacityUnit.MILILITER;
		Capacity capacity = Capacity.of(amount, units);
        MedicinePackage properMedicinePacage = MedicinePackage.of(medicine, capacity);
        medicineDoser.add(properMedicinePacage);

        Receipe properReceipe = Receipe
            .of(medicine, Dose.of(capacity, Period.of(amount, TimeUnit.MILLISECONDS)), 1);
        DosingResult dosingResult = medicineDoser.dose(
            properReceipe);

		assertEquals(DosingResult.SUCCESS, dosingResult);
	}

	@Test
	void whenMedicinePackageIsInsufficientMedicine_ShouldThrowInsufficientMedicineException() {
		String MedicineName = "med";
		int amount = 10;
		Medicine medicine = Medicine.of(MedicineName);
		CapacityUnit units = CapacityUnit.MILILITER;
		Capacity capacity = Capacity.of(amount, units);
        MedicinePackage InsufficientMedicinePackage = MedicinePackage.of(medicine, capacity);
        medicineDoser.add(InsufficientMedicinePackage);


        Receipe properReceipe = Receipe
            .of(medicine, Dose.of(capacity, Period.of(amount, TimeUnit.MILLISECONDS)), 2);

		Assertions.assertThrows(InsufficientMedicineException.class,() -> medicineDoser.dose(properReceipe));

	}

	@Test
	void whenReceiptAndMedicinePackageAreProperShouldMethodsCallInProperOrder() {
		String MedicineName = "med";
		int amount = 100;
		Medicine medicine = Medicine.of(MedicineName);
		CapacityUnit units = CapacityUnit.MILILITER;
		Capacity capacity = Capacity.of(amount, units);
		MedicinePackage properMedicinePacage = MedicinePackage.of(medicine, capacity);
		medicineDoser.add(properMedicinePacage);

		Receipe properReceipe = Receipe
			.of(medicine, Dose.of(capacity, Period.of(amount, TimeUnit.MILLISECONDS)), 1);
		DosingResult dosingResult = medicineDoser.dose(
			properReceipe);

		InOrder methodsCallOrder = Mockito.inOrder(engine,waterPump);
		int timeOfMediumProgram = MEDIUM_PROGRAM.getTimeInMinutes();
		methodsCallOrder.verify(waterPump).pour(5);
		methodsCallOrder.verify(engine).runWashing(timeOfMediumProgram);
		methodsCallOrder.verify(waterPump).release();
		methodsCallOrder.verify(engine).spin();
	}




}
