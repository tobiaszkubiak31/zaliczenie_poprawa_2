package edu.iis.mto.testreactor.doser;

public enum CapacityUnit {

    LITER(1000),
    MILILITER(1);

    private int value;

    CapacityUnit(int value) {
        this.value = value;

    }

    public int getValue() {
        return value;
    }
}
