package com.doan.qlns.controller.User;

import com.doan.qlns.DateUtils;
import com.doan.qlns.models.Attendance;
import com.doan.qlns.models.Employee;
import com.doan.qlns.models.FormRequest;
import com.doan.qlns.models.WorkSchedule;
import com.doan.qlns.service.AttendanceService;
import com.doan.qlns.service.EmployeeService; // Đảm bảo bạn đã import service này
import com.doan.qlns.service.FormRequestService;
import com.doan.qlns.service.WorkScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.doan.qlns.DateUtils.convertDateToLocalDateTime;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private FormRequestService formRequestService;

    @Autowired
    private EmployeeService employeeService; // Thêm EmployeeService để lấy Employee

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private WorkScheduleService workScheduleService;

    @GetMapping("/index")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Lấy tên người dùng (username)

        // Tìm Employee dựa trên username
        Employee currentEmployee = employeeService.findByUsername(username);

        // Kiểm tra nếu currentEmployee là null
        if (currentEmployee == null) {
            return "redirect:/login"; // Chuyển hướng đến trang login
        }

        // Lấy danh sách biểu mẫu đã tạo bởi nhân viên hiện tại
        List<FormRequest> formRequests = formRequestService.getFormRequestsByEmployeeId(currentEmployee.getId());
        model.addAttribute("formRequests", formRequests);

        LocalDate today = LocalDate.now();
        List<Attendance> attendanceRecords = attendanceService.getAttendanceByEmployeeId(currentEmployee.getId());

        // Kiểm tra null cho attendanceRecords (nếu cần)
        if (attendanceRecords != null) {
            model.addAttribute("attendanceRecords", attendanceRecords);
        } else {
            model.addAttribute("attendanceRecords", Collections.emptyList()); // Hoặc xử lý khác nếu cần
        }

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
    @PostMapping("/checkin")
    public String checkIn(@ModelAttribute Attendance attendance, @AuthenticationPrincipal Employee employee) {
        // Lấy tên người dùng
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Tìm Employee dựa trên username
        Employee currentEmployee = employeeService.findByUsername(username);
        if (currentEmployee == null) {
            return "redirect:/login"; // Chuyển hướng đến trang login
        }

        // Lấy thời gian hiện tại
        LocalDateTime now = LocalDateTime.now();

        // Cập nhật thông tin attendance
        attendance.setEmployee(currentEmployee); // Gán employee vào attendance
        attendance.setAttendanceDate(now.toLocalDate()); // Gán ngày chấm công
        attendance.setCheckInTime(now.toLocalTime()); // Gán giờ check-in

        // Tìm WorkSchedule cho nhân viên và ngày hiện tại
        WorkSchedule currentSchedule = workScheduleService.findWorkScheduleByEmployeeAndDate(currentEmployee, now.toLocalDate());

        if (currentSchedule == null) {
            return "redirect:/employee/attendance?error=noSchedule"; // Xử lý nếu không tìm thấy lịch làm việc
        }

        attendance.setWorkSchedule(currentSchedule); // Gán lịch làm việc cho attendance
        attendanceService.checkIn(attendance); // Lưu thông tin attendance
        return "redirect:/user/index"; // Chuyển hướng sau khi lưu
    }

}
