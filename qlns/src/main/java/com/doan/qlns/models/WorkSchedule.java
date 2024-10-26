package com.doan.qlns.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate; // Ngày bắt đầu
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate; // Ngày kết thúc
    private String shift;

}
