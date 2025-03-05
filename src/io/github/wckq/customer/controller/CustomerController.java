package io.github.wckq.customer.controller;

import io.github.wckq.customer.CustomerRepository;

public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean deposit(int customerId, int amount) {
        // TODO: implement the logic
        return true;
    }

    public boolean withdraw(int customerId, int amount) {
        // TODO: implement the logic
        return true;
    }
}
