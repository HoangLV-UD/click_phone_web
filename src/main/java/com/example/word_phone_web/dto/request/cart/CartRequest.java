package com.example.word_phone_web.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author: hieu
 * @since: 21/07/2022
 * Project_name: com.example.word_phone_web.dto.request.cart
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private String id;
    private String idProduct;
    private String quantityCart;
}
