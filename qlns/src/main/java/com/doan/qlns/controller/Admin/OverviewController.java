package com.doan.qlns.controller.Admin;
import com.doan.qlns.models.Employee;
import com.doan.qlns.models.Role;
import com.doan.qlns.service.EmployeeService;
import com.doan.qlns.service.RoleService;
import jakarta.validation.Valid;
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
@RequestMapping("/admin/overview")
public class OverviewController {
    @GetMapping
    public String overview(Model model) {
        return "admin/overview/index";
    }
}
