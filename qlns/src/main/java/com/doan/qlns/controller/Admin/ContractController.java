package com.doan.qlns.controller.Admin;

import com.doan.qlns.models.Contract;
import com.doan.qlns.models.Employee;
import com.doan.qlns.repository.ContractRepository;
import com.doan.qlns.repository.EmployeeRepository;
import com.doan.qlns.service.ContractService;
import com.doan.qlns.service.EmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/contracts")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ContractController {
    ContractService contractService;
    EmployeeRepository employeeRepository;
    ContractRepository contractRepository;

    @GetMapping
    public String getAllContracts(Model model) {
        List<Contract> contracts = contractService.getAllContracts();
        model.addAttribute("contracts", contracts);
        return "admin/emloyee/index";
    }

    @GetMapping("/{id}")
    public String getContractById(@PathVariable Long id, Model model) {
        Contract contract = contractService.getContractById(id).orElse(null);
        model.addAttribute("contract", contract);
        return "admin/contract/show"; // trả về view chi tiết hợp đồng
    }

    @GetMapping("/add")
    public String createContractForm(Model model) {
        List<Employee> employeesWithoutContract = employeeRepository.findEmployeesWithoutContract();
        // Truyền danh sách nhân viên tới view
        model.addAttribute("employees", employeesWithoutContract);
        model.addAttribute("contract", new Contract());
        return "admin/contract/add"; // trả về view tạo hợp đồng
    }

    @PostMapping("/add")
    public String createContracts(
            @RequestParam("contractType") String contractType,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam("employeeIds") List<Long> employeeIds,
            Model model) {

        for (Long employeeId : employeeIds) {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new RuntimeException("Employee not found with id " + employeeId));

            // Tạo hợp đồng cho từng nhân viên
            Contract contract = new Contract();
            contract.setEmployee(employee);
            contract.setContractType(contractType);
            contract.setStartDate(startDate);
            contract.setEndDate(endDate);

            // Lưu hợp đồng vào cơ sở dữ liệu
            contractRepository.save(contract);
        }

        // Thêm thông báo thành công và trả về trang kết quả
        model.addAttribute("message", "Hợp đồng đã được tạo thành công cho các nhân viên.");
        return "redirect:/admin/employees"; // Điều hướng về trang thành công
    }


    @GetMapping("/edit/{id}")
    public String editContractForm(@PathVariable Long id, Model model) {
        Contract contract = contractService.getContractById(id).orElse(null);
        model.addAttribute("contract", contract);
        return "contract/edit"; // trả về view chỉnh sửa hợp đồng
    }

    @PostMapping("/{id}")
    public String updateContract(@PathVariable Long id, @ModelAttribute Contract contractDetails) {
        contractService.updateContract(id, contractDetails);
        return "redirect:/admin/contracts"; // chuyển hướng về danh sách hợp đồng
    }

    @GetMapping("/delete/{id}")
    public String deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
        return "redirect:/admin/contracts"; // chuyển hướng về danh sách hợp đồng
    }
}
