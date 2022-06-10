package com.example.word_phone_web.dto.respone.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPropertyRespone {
    private String id;
    private String priceString;
    private long price;
    private long quantity;
    private String status;
    private String color;
}
