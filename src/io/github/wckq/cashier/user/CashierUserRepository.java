package io.github.wckq.cashier.user;

import java.util.ArrayList;
import java.util.List;

public class CashierUserRepository {

    private final List<CashierUser> cashier;

    public CashierUserRepository() {
        this.cashier = new ArrayList<>();
    }

    /**
     * Adds a cashier user to the repository.
     *
     * @param cashier the cashier user to add
     */
    public void addCashier(CashierUser cashier) {
        this.cashier.add(cashier);
    }

    /**
     * Removes a cashier user from the repository.
     *
     * @param cashier the cashier user to remove
     */
    public void removeCashier(CashierUser cashier) {
        this.cashier.remove(cashier);
    }


    /**
     * Finds a cashier user by its ID.
     *
     * @param id the ID of the cashier user to find
     * @return the cashier user with the given ID, or null if not found
     */
    public CashierUser findById(int id) {
        return cashier.stream().filter(customer -> customer.getId() == id).findFirst().orElse(null);
    }

    /**
     * Checks if a cashier user with the given ID exists in the repository.
     *
     * @param id the ID of the cashier user to check
     * @return true if the cashier user with the given ID exists, false otherwise
     */
    public boolean existsById(int id) {
        return cashier.stream().anyMatch(customer -> customer.getId() == id);
    }

    /**
     * Gets a list of all cashier users in the repository.
     *
     * @return a list of all cashier users in the repository
     */
    public List<CashierUser> getCashier() {
        return cashier;
    }
}
