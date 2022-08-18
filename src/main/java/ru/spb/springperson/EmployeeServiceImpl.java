package ru.spb.springperson;

import org.springframework.stereotype.Service;
import ru.spb.springperson.exeptions.EmployeeAlreadyAddedException;
import ru.spb.springperson.exeptions.EmployeeNotFoundException;
import ru.spb.springperson.exeptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeesList = new ArrayList<Employee>(10);

    @Override
    public Employee addEmployee(Employee employee) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        if (employeesList.size() == 10) {
            throw new EmployeeStorageIsFullException();
        } else if (findEmployee(employee) != -1) {
            throw new EmployeeAlreadyAddedException();
        } else {
            employeesList.add(employee);
            return employee;
        }
    }

/*    public String getEmployeesByDepartment() {
        Map<String, List<Employee>> getEmployeesByDepartment = employeesList.stream()
                .collect(employee::getDepartment);
        return getEmployeesByDepartment.toString();
    }*/

    @Override
    public Employee deleteEmployee(Employee employee) throws EmployeeNotFoundException {
        int index = findEmployee(employee);
        if (index == -1) {
            throw new EmployeeNotFoundException();
        } else {
            Employee foundEmployee = employeesList.get(index);
            employeesList.remove(index);
            return foundEmployee;
        }
    }

    @Override
    public Employee getEmployee(Employee employee) throws EmployeeNotFoundException {
        if (findEmployee(employee) == -1) {
            throw new EmployeeNotFoundException();
        } else {
            return employeesList.get(findEmployee(employee));
        }
    }

    private int findEmployee(Employee employee) {
        for (int i = 0; i < employeesList.size(); i++) {
            if (employeesList.get(i).equals(employee)) {
                return i;
            }
        }
        return -1;
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }
}
