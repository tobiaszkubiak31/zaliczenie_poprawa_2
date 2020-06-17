package edu.iis.mto.testreactor.atm;

public class ATMOperationException extends Exception {

    private final ErrorType errorType;

    public ATMOperationException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

}
