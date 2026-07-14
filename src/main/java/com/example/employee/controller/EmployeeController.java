package com.example.employee.controller;

import com.example.employee.Entity.Employee;
import com.example.employee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/emp")
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;
    

    @PostMapping()
    public Employee createEmployee(@RequestBody Employee emp){
       return employeeService.saveEmployee(emp);

    }

    @GetMapping()
    public List<Employee> getAllEmployee(){
        return employeeService.retrieveEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmpById(@PathVariable int id){
        return employeeService.retrieveEmpById(id);
    }

    @PutMapping("/{id}")
    public Employee updEmpById(@PathVariable int id, @RequestBody Employee emp){
        return employeeService.updateEmpById(id,emp);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEmployee(@PathVariable int id){
        return employeeService.deleteById(id);
    }
}

