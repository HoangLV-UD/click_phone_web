package com.example.word_phone_web.api;

import com.example.word_phone_web.entity.CustomerEntity;
import com.example.word_phone_web.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author: hieu
 * @since: 19/07/2022
 * Project_name: com.example.word_phone_web.api
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AccountApi {
    private final CustomerService staffService;
    @GetMapping("/count_login_false")
    public ResponseEntity<String> getCountLoginFalse(@RequestParam("email") String email) {
        CustomerEntity staff = staffService.findByEmail(email);
        if (staff != null) {
            System.out.println(staff.getId());
            return ResponseEntity.status(HttpStatus.OK).body("OKE");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sai tài khoản hoặc mật khẩu");
    }
}
