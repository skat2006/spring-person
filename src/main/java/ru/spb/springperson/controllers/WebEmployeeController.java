package ru.spb.springperson.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spb.springperson.Employee;
import ru.spb.springperson.EmployeeService;
import ru.spb.springperson.exeptions.EmployeeAlreadyAddedException;
import ru.spb.springperson.exeptions.EmployeeNotFoundException;
import ru.spb.springperson.exeptions.EmployeeStorageIsFullException;

@Controller
@RequestMapping(path = "/person")
public class WebEmployeeController {
    private final EmployeeService employeeService;

    public WebEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String addForm(Model model) {
        model.addAttribute("employee", new Employee(null, null, null,null));
        return "employee";
    }

    @PostMapping("/add")
    public String addSubmit(@ModelAttribute Employee employee, Model model) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        model.addAttribute("employee", employee);
        model.addAttribute("employeesList", employeeService.getEmployeesList());
        employeeService.addEmployee(employee);
        return "result";
    }

    @PostMapping("/remove")
    public String delSubmit(@ModelAttribute Employee employee, Model model) throws EmployeeNotFoundException {
        model.addAttribute("employee", employee);
        model.addAttribute("employeesList", employeeService.getEmployeesList());
        employeeService.deleteEmployee(employee);
        return "result";
    }

    @GetMapping("/result")
    public String resultShow(@ModelAttribute Employee employee, Model model) {
        model.addAttribute("employee", employee);
        model.addAttribute("employeesList", employeeService.getEmployeesList());
        return "result";
    }

    @ResponseBody
    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public String alreadyAdded() {
        return "Employee already added<br><br><a href=\"/person\">Назад</a>";
    }

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String notFound() {
        return "Employee not found<br><br><a href=\"/person\">Назад</a>";
    }

    @ResponseBody
    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public String storageIsFull() {
        return "Employee storage is full<br><br><a href=\"/person\">Назад</a>";
    }
}
