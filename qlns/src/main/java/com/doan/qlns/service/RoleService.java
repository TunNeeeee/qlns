package com.doan.qlns.service;


import com.doan.qlns.models.Employee;
import com.doan.qlns.models.Role;
import com.doan.qlns.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private IRoleRepository roleRepository;
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    public Role findByRoleID(Integer idRole) {
        return roleRepository.findRoleById(idRole);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
