package ru.spb.springperson;

public class Employee {
    private String lastName;
    private String firstName;

    public Employee(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Employee employee) {
            return this.lastName.equals(employee.lastName) && this.firstName.equals(employee.firstName);
        } else {
            return false;
        }
    }
}
