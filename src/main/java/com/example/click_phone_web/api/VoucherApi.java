package com.example.click_phone_web.api;

import com.example.click_phone_web.dto.respone.voucher.VoucherRespone;
import com.example.click_phone_web.service.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/voucher")
public class VoucherApi {
    private final VoucherService service;

    @GetMapping("{code}")
    public ResponseEntity<?> findByCode(@PathVariable("code") String code){
        VoucherRespone respone = service.findByCode(code);
        if(respone == null){
            return  ResponseEntity.badRequest().body(respone);
        }
        return  ResponseEntity.ok().body(respone);
    }
}
