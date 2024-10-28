package com.doan.qlns.controller.Admin;

import com.doan.qlns.models.Employee;
import com.doan.qlns.models.WorkSchedule;
import com.doan.qlns.service.EmployeeService;
import com.doan.qlns.service.WorkScheduleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/schedule")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AdminWorkScheduleController {
    WorkScheduleService workScheduleService;
    EmployeeService employeeService;

    @GetMapping("/create")
    public String showCreateScheduleForm(Model model) {
        List<Employee> employees = employeeService.getAllEmployees(); // Lấy danh sách nhân viên
        model.addAttribute("employees", employees);
        return "admin/schedule/create"; // Trả về template tạo lịch làm việc
    }

    @PostMapping("/create")
    public String createWorkSchedule(@ModelAttribute WorkSchedule workSchedule, @RequestParam Long employeeId) {
        // Lấy ngày bắt đầu và ngày kết thúc dưới dạng LocalDate
        LocalDate startDate = workSchedule.getStartDate();
        LocalDate endDate = workSchedule.getEndDate();

        // Kiểm tra xem ngày bắt đầu và ngày kết thúc có hợp lệ không
        if (startDate != null && endDate != null && startDate.isBefore(endDate)) {
            workSchedule.setStartDate(startDate);
            workSchedule.setEndDate(endDate);

            // Lấy nhân viên từ ID
            Employee employee = employeeService.findEmployeeById(employeeId);
            workSchedule.setEmployee(employee);

            // Tạo lịch làm việc
            workScheduleService.createSchedule(workSchedule);
        } else {
            // Xử lý lỗi nếu ngày bắt đầu không hợp lệ
            return "redirect:/admin/schedule/create?error=invalidDates"; // Chuyển hướng về trang tạo lịch làm việc với thông báo lỗi
        }

        return "redirect:/admin/schedule/list"; // Chuyển hướng về danh sách lịch làm việc
    }


    @GetMapping("/list")
    public String getScheduleList(Model model) {
        List<WorkSchedule> workSchedules = workScheduleService.getAllSchedules();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Chuyển đổi lịch làm việc thành danh sách với ngày đã định dạng
        List<FormattedSchedule> formattedSchedules = workSchedules.stream()
                .map(schedule -> new FormattedSchedule(
                        schedule.getEmployee().getName(),
                        schedule.getShift(),
                        schedule.getStartDate() != null ? schedule.getStartDate().format(formatter) : "Chưa có ngày bắt đầu",
                        schedule.getEndDate() != null ? schedule.getEndDate().format(formatter) : "Chưa có ngày kết thúc"
                ))
                .collect(Collectors.toList());

        model.addAttribute("workSchedules", formattedSchedules); // Thêm danh sách đã định dạng vào model
        return "admin/schedule/list"; // Trả về view
    }

    public static class FormattedSchedule {
        private String employeeName;
        private String shift;
        private String startDate;
        private String endDate;

        public FormattedSchedule(String employeeName, String shift, String startDate, String endDate) {
            this.employeeName = employeeName;
            this.shift = shift;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        // Getters và setters
        public String getEmployeeName() {
            return employeeName;
        }

        public String getShift() {
            return shift;
        }

        public String getStartDate() {
            return startDate;
        }

        public String getEndDate() {
            return endDate;
        }
    }
}
