package com.doan.qlns.service;

import com.doan.qlns.models.Employee;
import com.doan.qlns.models.WorkSchedule;
import com.doan.qlns.repository.WorkScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WorkScheduleService {
    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    public WorkSchedule createSchedule(WorkSchedule schedule) {
        return workScheduleRepository.save(schedule);
    }

    // Thêm phương thức để lấy tất cả lịch làm việc
    public List<WorkSchedule> getAllSchedules() {
        return workScheduleRepository.findAll();
    }

    public List<WorkSchedule> findSchedulesByEmployee(Employee employee) {
        return workScheduleRepository.findAllByEmployee(employee);
    }

    public WorkSchedule findWorkScheduleByEmployeeAndDate(Employee employee, LocalDate currentDate) {
        Optional<WorkSchedule> workScheduleOptional = workScheduleRepository.findByEmployeeAndDate(employee.getId(), currentDate);
        return workScheduleOptional.orElse(null); // Trả về null nếu không tìm thấy
    }
}