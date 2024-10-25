package com.doan.qlns.service;

import com.doan.qlns.models.Employee;
import com.doan.qlns.models.FormRequest;
import com.doan.qlns.repository.FormRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FormRequestService {
    @Autowired
    private FormRequestRepository formRequestRepository;

    // Tạo biểu mẫu và liên kết với nhân viên hiện tại
    public FormRequest createFormRequest(FormRequest formRequest, Employee currentEmployee) {
        formRequest.setEmployee(currentEmployee);
        // Gán ngày tạo
        formRequest.setCreationDate(new Date());
        return formRequestRepository.save(formRequest);
    }

    // Lưu biểu mẫu (có thể dùng để cập nhật)
    public void saveFormRequest(FormRequest formRequest) {
        formRequestRepository.save(formRequest);
    }

    // Lấy tất cả biểu mẫu theo nhân viên
    public List<FormRequest> findAllByEmployee(Employee employee) {
        return formRequestRepository.findAllByEmployee(employee);
    }
    public List<FormRequest> getAllFormRequests() {
        return formRequestRepository.findAll();
    }
    public void updateStatus(Long id, Integer status) {
        FormRequest formRequest = formRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("Form not found"));
        formRequest.setStatus(status);
        formRequestRepository.save(formRequest);
    }

    // Lấy danh sách biểu mẫu đã tạo bởi nhân viên theo ID
    public List<FormRequest> getFormRequestsByEmployeeId(Long employeeId) {
        return formRequestRepository.findByEmployeeId(employeeId);
    }
}
