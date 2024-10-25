package com.doan.qlns.controller.User;

import com.doan.qlns.models.Employee;
import com.doan.qlns.models.FormRequest;
import com.doan.qlns.service.EmployeeService; // Đảm bảo bạn đã import service này
import com.doan.qlns.service.FormRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private FormRequestService formRequestService;

    @Autowired
    private EmployeeService employeeService; // Thêm EmployeeService để lấy Employee

    @GetMapping("/index")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Lấy tên người dùng (username)

        // Tìm Employee dựa trên username
        Employee currentEmployee = employeeService.findByUsername(username);

        // Lấy danh sách biểu mẫu đã tạo bởi nhân viên hiện tại
        List<FormRequest> formRequests = formRequestService.getFormRequestsByEmployeeId(currentEmployee.getId());
        model.addAttribute("formRequests", formRequests);
        return "user/index";
    }

    @GetMapping("/form-request")
    public String showFormRequestPage(Model model) {
        model.addAttribute("formRequest", new FormRequest());
        return "user/formrequest/index"; // Trả về tên trang HTML
    }

    @PostMapping("/form-request")
    public String submitFormRequest(@Valid @ModelAttribute("formRequest") FormRequest formRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/formrequest/index"; // Trả lại biểu mẫu nếu có lỗi
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Lấy tên người dùng (username)

        // Tìm Employee dựa trên username
        Employee currentEmployee = employeeService.findByUsername(username);

        if (currentEmployee != null) {
            formRequestService.createFormRequest(formRequest, currentEmployee); // Lưu biểu mẫu với nhân viên hiện tại
            return "redirect:/user/form-request?success"; // Chuyển hướng đến trang đăng ký với thông báo thành công
        } else {
            // Xử lý lỗi nếu không tìm thấy Employee
            bindingResult.rejectValue("employee", "error.formRequest", "Không tìm thấy thông tin nhân viên.");
            return "user/formrequest/index"; // Trả lại biểu mẫu nếu có lỗi
        }
    }

    // Uncomment the method below if you want to implement history viewing functionality.
    /*
    @GetMapping("/form-requests/history")
    public String viewFormRequestsHistory(Model model) {
        // Implement logic to retrieve and display form request history if needed
        return "user/formrequest/history"; // Trả về trang lịch sử tạo biểu mẫu
    }
    */
}
