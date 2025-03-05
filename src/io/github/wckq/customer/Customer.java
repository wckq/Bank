package io.github.wckq.customer;

import io.github.wckq.person.Person;

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

    public String getRfc() {
        return rfc;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public CustomerType getType() {
        return type;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getFirstName();
    }
}
