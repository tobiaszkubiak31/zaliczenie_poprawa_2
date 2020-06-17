package edu.iis.mto.testreactor.atm.bank;

import edu.iis.mto.testreactor.atm.Card;
import edu.iis.mto.testreactor.atm.Money;
import edu.iis.mto.testreactor.atm.PinCode;

public interface Bank {

    AuthorizationToken autorize(PinCode pin, Card card) throws AuthorizationException;

    void charge(AuthorizationToken token, Money amount) throws AccountException;

    void commit(AuthorizationToken token);

    void cancel(AuthorizationToken token);
}
