package com.doan.qlns.repository;

import com.doan.qlns.models.Attendance;
import com.doan.qlns.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
//    List<Attendance> findAllByEmployeeAndAttendanceDate(Employee employee, LocalDate date);
    List<Attendance> findAttendanceByEmployeeId(Long employeeId);
}
