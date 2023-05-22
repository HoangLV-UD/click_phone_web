package com.example.click_phone_web.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private String id;
    private String idProduct;
    private String quantityCart;
    private Timestamp createDate;
}
