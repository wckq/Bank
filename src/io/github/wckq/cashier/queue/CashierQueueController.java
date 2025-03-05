package io.github.wckq.cashier.queue;

import io.github.wckq.customer.Customer;
import io.github.wckq.customer.CustomerType;

import java.util.List;
import java.util.Queue;

public class CashierQueueController {

    private final CashierQueueRepository cashierQueueRepository;

    public CashierQueueController(CashierQueueRepository cashierQueueRepository) {
        this.cashierQueueRepository = cashierQueueRepository;
    }

    public void addCustomer(Customer customer, CustomerType customerType) {
        switch (customerType) {
            case NORMAL -> this.cashierQueueRepository.getBasicCustomers().add(customer);
            case PREFERRED -> this.cashierQueueRepository.getPreferredCustomers().add(customer);
            case VIP -> this.cashierQueueRepository.getVipCustomers().add(customer);
            default -> throw new IllegalArgumentException("Customer type no valido.");
        }
    }

    public void removeCustomer(Customer customer, CustomerType customerType) {
        switch (customerType) {
            case NORMAL -> this.cashierQueueRepository.getBasicCustomers().remove(customer);
            case PREFERRED -> this.cashierQueueRepository.getPreferredCustomers().remove(customer);
            case VIP -> this.cashierQueueRepository.getVipCustomers().remove(customer);
            default -> throw new IllegalArgumentException("Customer type no valido.");
        }
    }
}
