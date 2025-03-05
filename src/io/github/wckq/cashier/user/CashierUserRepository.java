package io.github.wckq.cashier.user;

import java.util.ArrayList;
import java.util.List;

public class CashierUserRepository {

    private final List<CashierUser> cashier;

    public CashierUserRepository() {
        this.cashier = new ArrayList<>();
    }

    public void addCashier(CashierUser cashier) {
        this.cashier.add(cashier);
    }

    public void removeCashier(CashierUser cashier) {
        this.cashier.remove(cashier);
    }

    public CashierUser findById(int id) {
        return cashier.stream().filter(customer -> customer.getId() == id).findFirst().orElse(null);
    }

    public boolean existsById(int id) {
        return cashier.stream().anyMatch(customer -> customer.getId() == id);
    }

    public List<CashierUser> getCashier() {
        return cashier;
    }
}
