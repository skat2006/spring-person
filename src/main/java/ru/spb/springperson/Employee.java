package ru.spb.springperson;

public class Employee {
    private String lastName;
    private String firstName;
    private String salary;
    private String department;

    public Employee(String lastName, String firstName, String salary, String department) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.salary = salary;
        this.department = department;
    }
    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
