package com.doan.qlns.controller.Admin;

import com.doan.qlns.models.Contract;
import com.doan.qlns.models.Employee;
import com.doan.qlns.models.FormRequest;
import com.doan.qlns.models.Role;
import com.doan.qlns.repository.ContractRepository;
import com.doan.qlns.service.ContractService;
import com.doan.qlns.service.EmployeeService;
import com.doan.qlns.service.FormRequestService;
import com.doan.qlns.service.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/employees")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class EmployeeController {
    EmployeeService employeeService;
    RoleService roleService;
    PasswordEncoder passwordEncoder;
    ContractService contractService;
    FormRequestService formRequestService;

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        List<Contract> contracts = contractService.getAllContracts();
        List<FormRequest> formRequests = formRequestService.getAllFormRequests();
        model.addAttribute("contracts", contracts);
        model.addAttribute("formRequests", formRequests);
        return "admin/employee/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "admin/employee/add";
    }

    @PostMapping("/new")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {

        if (employee.getPassword() != null && !employee.getPassword().isEmpty()) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        }
        employeeService.saveEmployee(employee);
        return "redirect:/admin/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        model.addAttribute("roles", roleService.getAllRole());
        return "admin/employee/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @RequestParam List<Integer> roleIds, @Valid @ModelAttribute Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/employees/edit"; // Return the form view with error messages
        }
        employee.setId(id); // Ensure the employee ID is set for the update
        if (employee.getPassword() != null && !employee.getPassword().isEmpty()) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword())); // Băm mật khẩu mới
        } else {
            // Nếu không có mật khẩu mới, có thể giữ nguyên mật khẩu cũ
            Employee existingEmployee = employeeService.getEmployeeById(id);
            employee.setPassword(existingEmployee.getPassword()); // Giữ mật khẩu cũ
        }
        Set<Role> roles = new HashSet<>();
        for (Integer roleId : roleIds) {
            Role role = roleService.findByRoleID(roleId);
            roles.add(role);
        }

        // Gán lại vai trò cho nhân viên
        employee.setRoles(roles);
        employeeService.saveEmployee(employee); // Save updated employee
        return "redirect:/admin/employees"; // Redirect to the employee list
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/admin/employees";
    }
}
