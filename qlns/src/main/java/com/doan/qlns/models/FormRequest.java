package com.doan.qlns.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FormRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @NotNull
    private String formType; // Loại biểu mẫu (Ví dụ: Nghỉ phép, Yêu cầu khác)

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate; // Ngày tạo

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalDate; // Ngày duyệt

    private Integer status; // Trạng thái (chờ duyệt, đã duyệt, từ chối)

    private String additionalInfo; // Thông tin bổ sung (có thể null)

    // Constructors, Getters, Setters
}
