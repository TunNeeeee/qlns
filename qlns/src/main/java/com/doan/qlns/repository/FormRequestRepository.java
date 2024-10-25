package com.doan.qlns.repository;

import com.doan.qlns.models.Employee;
import com.doan.qlns.models.FormRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRequestRepository extends JpaRepository<FormRequest, Long> {
    List<FormRequest> findAllByEmployee(Employee employee);
    List<FormRequest> findByEmployeeId(Long employeeId);
}
