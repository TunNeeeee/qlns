package com.doan.qlns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class QlnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(QlnsApplication.class, args);
		// Tạo một instance của PasswordEncoder
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		// Mật khẩu gốc
		String rawPassword = "Admin@123";

		// Mã hóa mật khẩu
		String encodedPassword = passwordEncoder.encode(rawPassword);

		// In ra mật khẩu đã mã hóa
		System.out.println("Mật khẩu gốc: " + rawPassword);
		System.out.println("Mật khẩu đã mã hóa: " + encodedPassword);
	}

}
