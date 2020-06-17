package edu.iis.mto.testreactor.atm.bank;

import edu.iis.mto.testreactor.atm.Money;

public interface Bank {

    AuthorizationToken autorize(String pin, String cardNumber) throws AuthorizationException;

    void charge(AuthorizationToken token, Money amount) throws AccountException;

}
