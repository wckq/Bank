package io.github.wckq.customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private final List<Customer> customers;

    public CustomerRepository() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        this.customers.remove(customer);
    }

    public Customer findById(int id) {
        return customers.stream().filter(customer -> customer.getId() == id).findFirst().orElse(null);
    }

    public boolean existsById(int id) {
        return customers.stream().anyMatch(customer -> customer.getId() == id);
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
