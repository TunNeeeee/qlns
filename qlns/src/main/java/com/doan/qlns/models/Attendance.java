package com.doan.qlns.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Setter
@Getter
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate attendanceDate; // Ngày chấm công

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime checkInTime; // Thời gian vào

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime checkOutTime; // Thời gian ra

    @ManyToOne
    @JoinColumn(name = "work_schedule_id", nullable = false)
    private WorkSchedule workSchedule;
}
