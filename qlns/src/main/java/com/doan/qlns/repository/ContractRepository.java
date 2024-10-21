package com.doan.qlns.repository;

import com.doan.qlns.models.Contract;
import com.doan.qlns.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByEmployee(Employee employee);
}
