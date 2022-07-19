package com.example.word_phone_web.service;

import com.example.word_phone_web.dto.respone.attribute.AttributeRespone;

/**
 * Description:
 *
 * @author: hieu
 * @since: 17/07/2022
 * Project_name: com.example.word_phone_web.service
 */
public interface AttributeService {
    AttributeRespone findByProduct(Long id);
}
