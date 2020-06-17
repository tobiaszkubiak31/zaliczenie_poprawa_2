package edu.iis.mto.testreactor.atm;

public class ATMOperationException extends Exception {

    private static final long serialVersionUID = 1L;

    private final ErrorCode errorCode;

    public ATMOperationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
