package io.github.wckq.cashier.queue;

import io.github.wckq.customer.model.Customer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A repository for cashier queues.
 */
public class CashierQueueRepository {

    private final Queue<Customer> basicCustomers;
    private final Queue<Customer> preferredCustomers;
    private final Queue<Customer> vipCustomers;

    public CashierQueueRepository() {
        this.basicCustomers = new LinkedList<>();
        this.preferredCustomers = new LinkedList<>();
        this.vipCustomers = new LinkedList<>();
    }

    /**
     * Gets the basic customers queue.
     * @return the basic customers queue
     */
    public Queue<Customer> getBasicCustomers() {
        return basicCustomers;
    }

    /**
     * Gets the preferred customers queue.
     * @return the preferred customers queue
     */
    public Queue<Customer> getPreferredCustomers() {
        return preferredCustomers;
    }

    /**
     * Gets the VIP customers queue.
     * @return the VIP customers queue
     */
    public Queue<Customer> getVipCustomers() {
        return vipCustomers;
    }
}
