package com.example.employee.Service;

import com.example.employee.Entity.Employee;
import com.example.employee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    public EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee emp) {
        return employeeRepository.save(emp);
    }

    public List<Employee> retrieveEmployees() {
        return employeeRepository.findAll();
    }

    public Employee retrieveEmpById(int id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        return emp.orElse(null);
    }

    public Employee updateEmpById(int id, Employee emp) {
        Optional<Employee> existEmploy = employeeRepository.findById(id);

        if (existEmploy.isPresent()) {
            Employee empToUpdate = existEmploy.get();
            empToUpdate.setEmpName(emp.getEmpName());
            empToUpdate.setEmpSalary(emp.getEmpSalary());
            return employeeRepository.save(empToUpdate);
        }
        return null;
    }

    public boolean deleteById(int id) {
        Optional<Employee> byId = employeeRepository.findById(id);
        if (byId.isPresent()){
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
