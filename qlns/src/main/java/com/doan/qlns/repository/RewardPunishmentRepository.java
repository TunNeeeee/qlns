package com.doan.qlns.repository;

import com.doan.qlns.models.Employee;
import com.doan.qlns.models.RewardPunishment;
import com.doan.qlns.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardPunishmentRepository extends JpaRepository<RewardPunishment, Long> {
    List<RewardPunishment> findByEmployee(Employee employee);
    List<RewardPunishment> findByType(Type type);
}