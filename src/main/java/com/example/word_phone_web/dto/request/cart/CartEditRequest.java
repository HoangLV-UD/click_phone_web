package com.example.word_phone_web.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;

/**
 * Description:
 *
 * @author: hieu
 * @since: 23/07/2022
 * Project_name: com.example.word_phone_web.dto.request.cart
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartEditRequest {
    private String id;
    private String quantity;
}
