package com.example.word_phone_web.service.impl;

import com.example.word_phone_web.constant.AttributeConstant;
import com.example.word_phone_web.dto.respone.cart.CartRespone;
import com.example.word_phone_web.dto.respone.voucher.VoucherRespone;
import com.example.word_phone_web.entity.*;
import com.example.word_phone_web.repo.*;
import com.example.word_phone_web.service.VoucherService;
import com.example.word_phone_web.util.ConvertUtil;
import com.example.word_phone_web.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Description:
 *
 * @author: hieu
 * @since: 07/08/2022
 * Project_name: com.example.word_phone_web.service.impl
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class IVoucherServiceImpl implements VoucherService {
    private final VoucherRepo repo;

    private final CartRepo cartRepo;

    private final CustomerRepo customerRepo;

    private final SessionUtil sessionUtil;

    private final ProductRepo productRepo;

    private final ProductPropertyRepo productPropertyRepo;

    private final RomRepo romRepo;

    private final CategoryRepo categoryRepo;

    private final ColorServiceImpl colorService;
    private final ConvertUtil convertUtil;
    @Override
    public VoucherRespone findByCode(String code) {
        VoucherRespone respone = new VoucherRespone();
        VoucherEntity entity = repo.findByDeleteFlagIsFalseAndCode(code);
        if(entity == null){
            return null;
        }
        if(entity.getQuantity() == 0 || entity.getStatus().equals(AttributeConstant.OFF)){
            return null;
        }
        respone.setType(entity.getTypeDiscount());
        respone.setQuantity(String.valueOf(entity.getQuantity()));
        respone.setDiscount(String.valueOf(entity.getDiscount()));
        respone.setTypeDiscountMoneyMin(entity.getTypeDiscountMoneyMin());
        respone.setMinAmount(entity.getMinAmount());
        respone.setAccompanyPromo(entity.getAccompanyPromo());
        return respone;
    }


}
