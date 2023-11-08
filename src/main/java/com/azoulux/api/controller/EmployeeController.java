package com.azoulux.api.controller;

import com.azoulux.api.model.Employee;
import com.azoulux.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Read - Get all employees
     * @return - An Iterable object of Employee fulfilled
     */
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    /**
     * Read - Get an employee by id
     * @param id The id of the employee to retrieve
     * @return An Employee object fulfilled
     */
    @GetMapping("/employees/{id}")
    public  Employee getEmployee(@PathVariable("id") final long id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            return null;
        }
    }

    /**
     * Create - Add a new employee
     * @param employee An object employee
     * @return The employee object saved
     */
    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    /**
     * Update - Update an existing employee
     * @param id - The id of the employee to update
     * @param employee - The employee object updated
     */
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") final long id, @RequestBody Employee employee) {
        Optional<Employee> e = employeeService.getEmployee(id);
        if (e.isPresent()) {
            Employee currentEmploye = e.get();

            String firstname = employee.getFirstName();
            if (firstname != null) {
                currentEmploye.setFirstName(firstname);
            }

            String lastname = employee.getLastName();
            if (lastname != null) {
                currentEmploye.setLastName(lastname);
            }

            String mail = employee.getMail();
            if(mail != null) {
                currentEmploye.setMail(mail);
            }

            String password = employee.getPassword();
            if (password != null) {
                currentEmploye.setPassword(password);
            }
            employeeService.saveEmployee(currentEmploye);
            return currentEmploye;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an employee
     * @param id - The id of the employee to delete
     */
    @DeleteMapping
    public void deleteEmployee(@PathVariable("id") final long id) {
        employeeService.deleteEmployee(id);
    }

}
