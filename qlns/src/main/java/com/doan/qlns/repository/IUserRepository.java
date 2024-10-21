package com.doan.qlns.repository;

import com.doan.qlns.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByUsername(String username);
}