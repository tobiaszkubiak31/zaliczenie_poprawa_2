package edu.iis.mto.testreactor.doser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.iis.mto.testreactor.doser.infuser.Infuser;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MedicineDoserTest {
    @Mock
    private  Infuser infuser;
    @Mock
    private  Clock clock;
    @Mock
    private  DosageLog dosageLog;

    private final Map<Medicine, MedicinePackage> medicinesTray = new HashMap<>();
    private MedicineDoser medicineDoser;

    @BeforeEach
    public void setUp() {
        medicineDoser = new MedicineDoser(infuser,dosageLog,clock);
    }

    @Test
    void shouldProperDose() {

        assertEquals(2, 1 + 1);
    }
}
