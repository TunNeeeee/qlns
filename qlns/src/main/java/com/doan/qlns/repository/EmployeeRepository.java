package com.doan.qlns.repository;

import com.doan.qlns.models.Employee;

import com.doan.qlns.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByStatus(Status status);
    Employee findByUsername(String username);
    @Query("SELECT e FROM Employee e WHERE e.id NOT IN (SELECT c.employee.id FROM Contract c)")
    List<Employee> findEmployeesWithoutContract();
}