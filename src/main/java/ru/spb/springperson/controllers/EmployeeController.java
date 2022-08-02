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
                      @RequestParam("lastName") String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employee = new Employee(firstName,lastName);

        return employeeService.addEmployee(employee);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String firstName,
                      @RequestParam("lastName") String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName,lastName);
        ;
        return employeeService.deleteEmployee(employee);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName,
                      @RequestParam("lastName") String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName,lastName);
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
