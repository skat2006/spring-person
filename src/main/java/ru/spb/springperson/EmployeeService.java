package ru.spb.springperson;

import ru.spb.springperson.exeptions.EmployeeAlreadyAddedException;
import ru.spb.springperson.exeptions.EmployeeNotFoundException;
import ru.spb.springperson.exeptions.EmployeeStorageIsFullException;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException;
    Employee deleteEmployee(Employee employee) throws EmployeeNotFoundException;
    Employee getEmployee(Employee employee) throws EmployeeNotFoundException;
    List<Employee> getEmployeesList();
}
