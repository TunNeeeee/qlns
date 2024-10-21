package com.doan.qlns.repository;

import com.doan.qlns.models.Employee;

import com.doan.qlns.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByStatus(Status status);
}