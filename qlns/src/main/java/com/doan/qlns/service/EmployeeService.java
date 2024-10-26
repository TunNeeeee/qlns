package com.doan.qlns.service;

import com.doan.qlns.models.Employee;
import com.doan.qlns.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee findEmployeeById(Long employeeId) {
        // Lấy Employee từ ID, nếu không tồn tại thì có thể ném ra ngoại lệ
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        return employeeOpt.orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với ID: " + employeeId));
    }
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username); // Phương thức tìm kiếm Employee theo username
    }
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Employee not found"));
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}