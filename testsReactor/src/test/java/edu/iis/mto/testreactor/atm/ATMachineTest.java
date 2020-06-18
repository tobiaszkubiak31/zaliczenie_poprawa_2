package edu.iis.mto.testreactor.atm;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ATMachineTest {

    @BeforeEach
    public void setUp() throws Exception {}

    @Test
    public void itCompiles() {
        assertThat(true, Matchers.equalTo(true));
    }

}
