package io.github.wckq.cashier.user.controller;

import io.github.wckq.cashier.user.CashierUserRepository;

public class CashierUserController {

    private final CashierUserRepository cashierRepository;

    public CashierUserController(CashierUserRepository customerRepository) {
        this.cashierRepository = customerRepository;
    }

    public boolean receiveMoney(int customerId, int amount) {
        // TODO: implement the logic
        return true;
    }

    public boolean sendMoney(int customerId, int amount) {
        // TODO: implement the logic
        return true;
    }
}
