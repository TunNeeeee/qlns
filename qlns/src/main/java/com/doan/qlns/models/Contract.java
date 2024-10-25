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
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @NotNull
    private String contractType;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @PrePersist
    @PreUpdate
    private void validateDates() {
        if (endDate.before(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", employee=" + employee.getName() +
                ", contractType='" + contractType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
