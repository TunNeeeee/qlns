package com.doan.qlns.controller;


import com.doan.qlns.models.Employee;
import com.doan.qlns.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class LogInController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/register")
//    public String register(@NotNull Model model) {
//        model.addAttribute("user", new Employee());
//        return "sign-up";
//    }
//    @PostMapping("/register")
//    public String register(@Valid @ModelAttribute("user") Employee user, @NotNull BindingResult bindingResult,
//                           Model model) {
//        if (bindingResult.hasErrors()) { // Kiểm tra nếu có lỗi validate
//            var errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .toArray(String[]::new);
//            model.addAttribute("errors", errors);
//            return "sign-up";
//        }
//        userService.save(user);
//        userService.setDefaultRole(user.getUsername());
//        return "redirect:/login";
//    }
}




