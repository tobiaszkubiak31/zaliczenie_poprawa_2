package edu.iis.mto.testreactor.doser;

import java.util.Objects;

public class Medicine {

    private final String name;

    public static Medicine of(String name) {
        return new Medicine(Objects.requireNonNull(name, "name"));
    }

    private Medicine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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
        Medicine other = (Medicine) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Medicine [name=" + name + "]";
    }

}
