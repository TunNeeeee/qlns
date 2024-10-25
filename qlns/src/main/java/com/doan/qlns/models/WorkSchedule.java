package com.doan.qlns.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Date workDate; // Ngày làm việc

    @DateTimeFormat(pattern = "HH:mm")
    private Date startTime; // Giờ bắt đầu

    @DateTimeFormat(pattern = "HH:mm")
    private Date endTime; // Giờ kết thúc
}
