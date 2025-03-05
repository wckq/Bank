package io.github.wckq.cashier.user;

import io.github.wckq.person.Person;

/**
 * Represents a cashier user.
 */
public class CashierUser extends Person {

    private int initialCapital;
    private int finalCapital;

    public CashierUser(int id, String name, String firstName, String lastName) {
        super(id, name, firstName, lastName);
        this.initialCapital = 1000;
        this.finalCapital = 0;
    }

    /**
     * Sets the initial capital of the cashier user.
     *
     * @param initialCapital the initial capital of the cashier user
     */
    public void setInitialCapital(int initialCapital) {
        this.initialCapital = initialCapital;
    }

    /**
     * Sets the final capital of the cashier user.
     *
     * @param finalCapital the final capital of the cashier user
     */
    public void setFinalCapital(int finalCapital) {
        this.finalCapital = finalCapital;
    }

    /**
     * Gets the initial capital of the cashier user.
     *
     * @return the initial capital of the cashier user
     */
    public int getInitialCapital() {
        return initialCapital;
    }

    /**
     * Gets the final capital of the cashier user.
     *
     * @return the final capital of the cashier user
     */
    public int getFinalCapital() {
        return finalCapital;
    }
}
