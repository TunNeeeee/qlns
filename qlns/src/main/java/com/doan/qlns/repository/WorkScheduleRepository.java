package com.doan.qlns.repository;

import com.doan.qlns.models.Employee;
import com.doan.qlns.models.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> {
    List<WorkSchedule> findAllByEmployee(Employee employee);

    @Query("SELECT ws FROM WorkSchedule ws WHERE ws.employee.id = :employeeId AND :currentDate BETWEEN ws.startDate AND ws.endDate")
    Optional<WorkSchedule> findByEmployeeAndDate(@Param("employeeId") Long employeeId, @Param("currentDate") LocalDate currentDate);
}
