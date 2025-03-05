package io.github.wckq.cashier.queue.controller;

import io.github.wckq.cashier.queue.CashierQueueRepository;
import io.github.wckq.customer.CustomerType;
import io.github.wckq.customer.model.Customer;

public class CashierQueueController {

    private final CashierQueueRepository cashierQueueRepository;

    public CashierQueueController(CashierQueueRepository cashierQueueRepository) {
        this.cashierQueueRepository = cashierQueueRepository;
    }

    /**
     * Adds a customer to the specified queue.
     *
     * @param customer the customer to add
     * @param customerType the type of the customer
     */
    public void addCustomer(Customer customer, CustomerType customerType) {
        switch (customerType) {
            case NORMAL -> this.cashierQueueRepository.getBasicCustomers().add(customer);
            case PREFERRED -> this.cashierQueueRepository.getPreferredCustomers().add(customer);
            case VIP -> this.cashierQueueRepository.getVipCustomers().add(customer);
            default -> throw new IllegalArgumentException("Customer type no valido.");
        }
    }

    /**
     * Removes a customer from the specified queue.
     *
     * @param customer the customer to remove
     * @param customerType the type of the customer
     */
    public void removeCustomer(Customer customer, CustomerType customerType) {
        switch (customerType) {
            case NORMAL -> this.cashierQueueRepository.getBasicCustomers().remove(customer);
            case PREFERRED -> this.cashierQueueRepository.getPreferredCustomers().remove(customer);
            case VIP -> this.cashierQueueRepository.getVipCustomers().remove(customer);
            default -> throw new IllegalArgumentException("Customer type no valido.");
        }
    }
}
