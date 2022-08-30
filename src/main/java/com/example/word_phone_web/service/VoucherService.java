package com.example.word_phone_web.service;

import com.example.word_phone_web.dto.respone.voucher.VoucherRespone;

/**
 * Description:
 *
 * @author: hieu
 * @since: 07/08/2022
 * Project_name: com.example.word_phone_web.service
 */
public interface VoucherService {
    VoucherRespone findByCode(String code);
}
