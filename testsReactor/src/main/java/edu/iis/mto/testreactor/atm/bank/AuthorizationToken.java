package edu.iis.mto.testreactor.atm.bank;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class AuthorizationToken {

    private final String value;

    public static AuthorizationToken create(String value) {
        return new AuthorizationToken(requireNonNull(value));
    }

    private AuthorizationToken(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
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
        AuthorizationToken other = (AuthorizationToken) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "AuthorizationToken [value=" + value + "]";
    }

}
