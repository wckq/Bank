package io.github.wckq.customer;

import io.github.wckq.person.Person;

/**
 * Represents a customer.
 */
public class Customer extends Person {

    private final String rfc;
    private CustomerType type;
    private String address;
    private double salary;

    public Customer(int id, String name, String firstName, String lastName, String rfc) {
        super(id, name, firstName, lastName);
        this.rfc = rfc;
        this.address = "";
        this.type = CustomerType.NORMAL;
        this.salary = 0;
    }

    /**
     * Gets the RFC of the customer.
     *
     * @return the RFC of the customer
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Sets the address of the customer.
     *
     * @param address the address of the customer
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the address of the customer.
     *
     * @return the address of the customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the type of the customer.
     *
     * @param type the type of the customer
     */
    public void setType(CustomerType type) {
        this.type = type;
    }

    /**
     * Gets the type of the customer.
     *
     * @return the type of the customer
     */
    public CustomerType getType() {
        return type;
    }

    /**
     * Sets the salary of the customer.
     *
     * @param salary the salary of the customer
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Gets the salary of the customer.
     *
     * @return the salary of the customer
     */
    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getFirstName();
    }
}
