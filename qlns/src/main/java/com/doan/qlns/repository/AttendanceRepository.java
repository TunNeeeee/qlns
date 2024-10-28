package com.doan.qlns.repository;

import com.doan.qlns.models.Attendance;
import com.doan.qlns.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
//    List<Attendance> findAllByEmployeeAndAttendanceDate(Employee employee, LocalDate date);
    List<Attendance> findByEmployeeId(Long employeeId);
    Optional<Attendance> findByEmployeeAndAttendanceDate(Employee employee, LocalDate attendanceDate);
}
