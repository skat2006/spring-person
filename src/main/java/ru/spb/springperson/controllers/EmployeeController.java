package ru.spb.springperson.controllers;

import org.springframework.web.bind.annotation.*;
import ru.spb.springperson.Employee;
import ru.spb.springperson.EmployeeService;
import ru.spb.springperson.exeptions.EmployeeAlreadyAddedException;
import ru.spb.springperson.exeptions.EmployeeNotFoundException;
import ru.spb.springperson.exeptions.EmployeeStorageIsFullException;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("Salary") String salary,
                        @RequestParam("department") String department) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employee = new Employee(firstName, lastName, salary, department);

        return employeeService.addEmployee(employee);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName, "10000", "0");

        return employeeService.deleteEmployee(employee);
    }

    @GetMapping("/dep")
    public String getEmployeesByDepartment(@RequestParam("department") String department) {
        List<String> getEmployeesByDepartment = employeeService.getEmployeesList().stream()
                .map(Employee::getDepartment)
                .filter(dDepartment -> dDepartment.equals(department))
                .toList();

        return getEmployeesByDepartment.toString();
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("Salary") String salary,
                         @RequestParam("department") String department) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName, salary, department);
        return employeeService.getEmployee(employee);
    }

    @GetMapping("/getAll")
    public List<Employee> getAll() {
        return employeeService.getEmployeesList();
    }

    @ResponseBody
    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public String alreadyAdded() {
        return "Employee already added";
    }

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String notFound() {
        return "Employee not found";
    }

    @ResponseBody
    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public String storageIsFull() {
        return "Employee storage is full";
    }
}
