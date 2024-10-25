package com.doan.qlns.controller.Admin;
import com.doan.qlns.service.FormRequestService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class FromRequestController {
    @Autowired
    FormRequestService formRequestService;
    @PostMapping("/form-requests/approve/{id}")
    public String approveFormRequest(@PathVariable Long id) {
        formRequestService.updateStatus(id, 1); // 1 là trạng thái "Đã duyệt"
        return "redirect:/admin/employees#recruitment";
    }

    @PostMapping("/form-requests/reject/{id}")
    public String rejectFormRequest(@PathVariable Long id) {
        formRequestService.updateStatus(id, 0); // 0 là trạng thái "Không duyệt"
        return "redirect:/admin/employees#recruitment";
    }
}
