package com.doan.qlns.repository;

import com.doan.qlns.models.Employee;
import com.doan.qlns.models.EmploymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistory, Long> {
    List<EmploymentHistory> findByEmployee(Employee employee);
}
