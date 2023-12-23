package com.gichungasoftwares.employeesmanager;

import com.gichungasoftwares.employeesmanager.model.Employee;
import com.gichungasoftwares.employeesmanager.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee") //Give this class a default url for accessing this employee resource
public class EmployeeResource {
//    Bring in the service to be used in this class
    private final EmployeeService employeeService;

//    inject the service into the constructor, so we can autowire the service inside the class
    public EmployeeResource(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

//    get a list of all employees
//    this method will return an HTTP response
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees () {
//        call the service and have it return a list of all employees
        List<Employee> employees = employeeService.findAllEmployees();
        // return the employees and a http response
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    //    get the user with the provided id
//    this method will consume the id from the path variable and return an HTTP response
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById (@PathVariable("id") Long id) {
//        call the service and have it return a list of all employees
        Employee employee = employeeService.findEmployeeById(id);
        // return the employees and an HTTP response
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // add an employee to the database
    // the add employee method will consume the whole employee object from the request body
    // and remember that we set the uuid to the entity, so it will be part of the entity
    // this method will return the created employee and an employee-created response
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    // update an existing employee in the database
    // the update employee method will consume the whole employee object from the request body
    // this method will return the updated employee and an ok response
    @PutMapping ("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    // the delete method takes in the employee id from the request
    // this method does not return anything
    // the ? indicates that there is no entity returned
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
