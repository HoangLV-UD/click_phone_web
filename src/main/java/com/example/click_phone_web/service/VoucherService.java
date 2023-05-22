package com.example.click_phone_web.service;

import com.example.click_phone_web.dto.respone.voucher.VoucherRespone;

public interface VoucherService {
    VoucherRespone findByCode(String code);
}
