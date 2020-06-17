package edu.iis.mto.testreactor.atm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.iis.mto.testreactor.atm.bank.AccountException;
import edu.iis.mto.testreactor.atm.bank.AuthorizationException;
import edu.iis.mto.testreactor.atm.bank.AuthorizationToken;
import edu.iis.mto.testreactor.atm.bank.Bank;

public class ATMachine {

    private final Bank bank;

    private final MoneyDeposit deposit;

    public ATMachine(Bank bank, MoneyDeposit deposit) {
        this.bank = bank;
        this.deposit = deposit;
    }

    Withdrawal withdraw(PinCode pin, Card card, Money amount) throws ATMOperationException {
        AuthorizationToken token = authorize(pin, card);
        List<BanknotesPack> withdrawal = performTransaction(token, amount);
        return Withdrawal.create(withdrawal);
    }

    private AuthorizationToken authorize(PinCode pin, Card card) throws ATMOperationException {
        try {
            return bank.autorize(pin, card);
        } catch (AuthorizationException e) {
            throw new ATMOperationException(ErrorType.AHTHORIZATION);
        }
    }

    private List<BanknotesPack> performTransaction(AuthorizationToken token, Money amount) throws ATMOperationException {
        List<BanknotesPack> withdrawal = calculateWithdrawal(amount);
        if (availableInDeposit(withdrawal)) {
            performBankTransaction(token, amount);
            return withdrawal;
        }
        throw new ATMOperationException(ErrorType.NOT_ENOUGH_BANKNOTES);
    }

    private boolean availableInDeposit(List<BanknotesPack> withdrawal) {
        return withdrawal.stream()
                         .allMatch(banknotes -> deposit.isAvailable(banknotes));
    }

    private void performBankTransaction(AuthorizationToken token, Money amount) throws ATMOperationException {
        try {
            bank.charge(token, amount);
        } catch (AccountException e) {
            throw new ATMOperationException(ErrorType.NO_FUNDS_ON_ACCOUNT);
        }

    }

    private List<BanknotesPack> calculateWithdrawal(Money amount) throws ATMOperationException {
        if (unavailableCurrency(amount)) {
            throw new ATMOperationException(ErrorType.WRONG_CURRENCY);
        }
        int value = getValueToWithdraw(amount.getDenomination());
        List<Banknote> allBanknotesForCurrency = Banknote.getDescFor(amount.getCurrency());
        List<BanknotesPack> result = new ArrayList<>();

        for (Banknote banknote : allBanknotesForCurrency) {
            int denomination = banknote.getDenomination();
            int requiredCount = value / denomination;
            int availableCount = deposit.getAvailableCountOf(banknote);
            value = value % banknote.getDenomination();
            if (availableCount < requiredCount) {
                value += (requiredCount - availableCount) * denomination;
            }
            result.add(BanknotesPack.builder()
                                    .withCount((requiredCount - availableCount))
                                    .withDenomination(banknote)
                                    .build());
        }
        if (value > 0) {
            throw new ATMOperationException(ErrorType.WRONG_AMOUNT);
        }
        return result;
    }

    private int getValueToWithdraw(BigDecimal denomination) throws ATMOperationException {
        try {
            return denomination.intValueExact();
        } catch (ArithmeticException e) {
            throw new ATMOperationException(ErrorType.WRONG_AMOUNT);
        }
    }

    private boolean unavailableCurrency(Money amount) {
        return !deposit.getCurrency()
                       .equals(amount.getCurrency());
    }
}
