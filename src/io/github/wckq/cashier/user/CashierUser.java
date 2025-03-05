package io.github.wckq.cashier.user;

import io.github.wckq.person.Person;

public class CashierUser extends Person {

    private int initialCapital;
    private int finalCapital;

    public CashierUser(int id, String name, String firstName, String lastName) {
        super(id, name, firstName, lastName);
        this.initialCapital = 1000;
        this.finalCapital = 0;
    }

    public void setInitialCapital(int initialCapital) {
        this.initialCapital = initialCapital;
    }

    public void setFinalCapital(int finalCapital) {
        this.finalCapital = finalCapital;
    }

    public int getInitialCapital() {
        return initialCapital;
    }

    public int getFinalCapital() {
        return finalCapital;
    }
}
