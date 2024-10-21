package com.doan.qlns.models;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
@Setter
@Getter
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class RewardPunishment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private Type type;  // Reward or Punishment

    private String description;
    private Date date;

    // Getters, Setters, Constructors
}

