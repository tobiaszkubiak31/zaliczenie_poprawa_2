package edu.iis.mto.testreactor.doser;

public interface DosageLog {

    void logStartDose(Medicine medicine, Dose dose);

    void logEndDose(Medicine medicine, Dose dose);

    void logError(Receipe receipe, String message);

    void logDifuserError(Dose dose, String message);

    void logStart();

    void logEnd();

}
