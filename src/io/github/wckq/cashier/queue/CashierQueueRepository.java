package io.github.wckq.cashier.queue;

import io.github.wckq.customer.Customer;

import java.util.LinkedList;
import java.util.Queue;

public class CashierQueueRepository {

    private final Queue<Customer> basicCustomers;
    private final Queue<Customer> preferredCustomers;
    private final Queue<Customer> vipCustomers;

    public CashierQueueRepository() {
        this.basicCustomers = new LinkedList<>();
        this.preferredCustomers = new LinkedList<>();
        this.vipCustomers = new LinkedList<>();
    }

    public Queue<Customer> getBasicCustomers() {
        return basicCustomers;
    }

    public Queue<Customer> getPreferredCustomers() {
        return preferredCustomers;
    }

    public Queue<Customer> getVipCustomers() {
        return vipCustomers;
    }
}
