package edu.iis.mto.testreactor.atm;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Withdrawal {

    public static Withdrawal ZERO = new Withdrawal(List.of());
    private final List<Banknote> banknotes;

    public static Withdrawal create(List<BanknotesPack> banknotes) {

        return new Withdrawal(requireNonNull(banknotes).stream()
                                                       .flatMap(notes -> IntStream.range(0, notes.getCount())
                                                                                  .mapToObj(index -> notes.getDenomination()))
                                                       .collect(Collectors.toList()));
    }

    private Withdrawal(List<Banknote> banknotes) {
        this.banknotes = banknotes;
    }

    public List<Banknote> getBanknotes() {
        return banknotes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(banknotes);
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
        Withdrawal other = (Withdrawal) obj;
        return Objects.equals(banknotes, other.banknotes);
    }

    @Override
    public String toString() {
        return "Withdrawal [banknotes=" + banknotes + "]";
    }

}
