package com.doan.qlns.service;

import com.doan.qlns.models.Attendance;
import com.doan.qlns.models.Employee;
import com.doan.qlns.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance checkIn(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

//    public List<Attendance> getAttendanceByEmployee(Employee employee, LocalDate date) {
//        return attendanceRepository.findAllByEmployeeAndAttendanceDate(employee, date);
//    }
    public List<Attendance> getAttendanceByEmployeeId(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }
    public Optional<Attendance> findByEmployeeAndDate(Employee employee, LocalDate date) {
        return attendanceRepository.findByEmployeeAndAttendanceDate(employee, date);
    }
}
