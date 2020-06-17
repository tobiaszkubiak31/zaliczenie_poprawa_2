package edu.iis.mto.testreactor.atm;

import java.util.Objects;

public class PinCode {

    private static final String PIN_FORMAT = "%d%d%d%d";
    private final int dig1;
    private final int dig2;
    private final int dig3;
    private final int dig4;

    public static PinCode createPIN(int dig1, int dig2, int dig3, int dig4) {
        return new PinCode(dig1, dig2, dig3, dig4);
    }

    private PinCode(int dig1, int dig2, int dig3, int dig4) {
        this.dig1 = dig1;
        this.dig2 = dig2;
        this.dig3 = dig3;
        this.dig4 = dig4;
    }

    String getPIN() {
        return String.format(PIN_FORMAT, dig1, dig2, dig3, dig4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dig1, dig2, dig3, dig4);
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
        PinCode other = (PinCode) obj;
        return dig1 == other.dig1 && dig2 == other.dig2 && dig3 == other.dig3 && dig4 == other.dig4;
    }

    @Override
    public String toString() {
        return getPIN();
    }

}
