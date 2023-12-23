package com.gichungasoftwares.employeesmanager.service;

import com.gichungasoftwares.employeesmanager.exception.UserNotFoundException;
import com.gichungasoftwares.employeesmanager.model.Employee;
import com.gichungasoftwares.employeesmanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    //    inject the employee repository into the employee service
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    //create a method to set the UUID because it will not be provided by the user
    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString()); //using setters to set the uuid
        return employeeRepository.save(employee);
    }

    // this method returns a list of all employees
    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    // this method updates the employee with the provided id
    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long id){
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id " +id+ " was not found"));
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteEmployeeById(id)
                .orElseThrow(() -> new IllegalStateException("user with id " +id+ " not found"));
    }
}
