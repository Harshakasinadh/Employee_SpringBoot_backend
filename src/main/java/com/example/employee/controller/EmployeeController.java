package com.example.employee.controller;

import com.example.employee.Entity.Employee;
import com.example.employee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/emp")
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;


    @PostMapping()
    public ResponseEntity<?> createEmployee(@RequestBody Employee emp){
        try {
            Employee employee = employeeService.saveEmployee(emp);
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping()
    public ResponseEntity<?> getAllEmployee(){
        List<Employee> employees = employeeService.retrieveEmployees();
        if (employees != null && !employees.isEmpty()){
            return new ResponseEntity<>(employees,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Records found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable int id){
        Employee employee = employeeService.retrieveEmpById(id);
        if (employee != null){
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(employee, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updEmpById(@PathVariable int id, @RequestBody Employee emp){
        Employee employee = employeeService.updateEmpById(id, emp);
        if (employee != null){
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(id + " Not found to update", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id){
        boolean b = employeeService.deleteById(id);
        if (b == true) {
            return new ResponseEntity<>(id + " Deleted successfully", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Employee not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}

