package com.gichungasoftwares.employeesmanager.repository;

import com.gichungasoftwares.employeesmanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // this method is created automatically from the method defined in employee service
//    it creates a query
//    void deleteEmployeeById(Long employee);

    Optional<Employee> findEmployeeById(Long id);

    Optional<Employee> deleteEmployeeById(Long id);
}
