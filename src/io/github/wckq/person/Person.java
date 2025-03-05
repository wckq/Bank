package io.github.wckq.person;

public class Person {
    private final int id;
    private final String name;
    private final String firstName;
    private final String lastName;

    public Person(int id, String name, String firstName, String lastName) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
