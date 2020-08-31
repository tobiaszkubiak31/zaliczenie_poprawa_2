package edu.iis.mto.testreactor.doser;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.iis.mto.testreactor.doser.infuser.Infuser;

@ExtendWith(MockitoExtension.class)
public class MedicineDoserTest {

    @Mock
    private Infuser infuser;
    @Mock
    private Clock clock;
    @Mock
    private DosageLog dosageLog;

    @Test
    void test() {
        MedicineDoser doser = new MedicineDoser(infuser, dosageLog, clock);
        doser.add(MedicinePackage.of(Medicine.of("betaBlocker"), Capacity.of(1, CapacityUnit.LITER)));
        doser.dose(
                Receipe.of(Medicine.of("betaBlocker"), Dose.of(Capacity.of(10, CapacityUnit.MILILITER), Period.of(1, TimeUnit.HOURS)), 10));

    }
}
