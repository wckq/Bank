package io.github.wckq.customer;

import io.github.wckq.customer.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private final List<Customer> customers;

    public CustomerRepository() {
        this.customers = new ArrayList<>();
    }

    /**
     * Adds a customer to the repository.
     *
     * @param customer the customer to add
     */
    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    /**
     * Removes a customer from the repository.
     *
     * @param customer the customer to remove
     */
    public void removeCustomer(Customer customer) {
        this.customers.remove(customer);
    }

    /**
     * Finds a customer by its ID.
     *
     * @param id the ID of the customer to find
     * @return the customer with the given ID, or null if not found
     */
    public Customer findById(int id) {
        return customers.stream().filter(customer -> customer.getId() == id).findFirst().orElse(null);
    }

    /**
     * Checks if a customer with the given ID exists in the repository.
     *
     * @param id the ID of the customer to check
     * @return true if the customer with the given ID exists, false otherwise
     */
    public boolean existsById(int id) {
        return customers.stream().anyMatch(customer -> customer.getId() == id);
    }

    /**
     * Gets a list of all customers in the repository.
     *
     * @return a list of all customers in the repository
     */
    public List<Customer> getCustomers() {
        return customers;
    }
}
