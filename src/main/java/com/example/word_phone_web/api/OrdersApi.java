package com.example.word_phone_web.api;

import com.example.word_phone_web.dto.request.orders.OrdersRequest;
import com.example.word_phone_web.dto.respone.VPResponDto;
import com.example.word_phone_web.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *
 * @author: hieu
 * @since: 12/08/2022
 * Project_name: com.example.word_phone_web.api
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersApi {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrdersRequest request){
        String check = orderService.createOrder(request);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }else {
            return ResponseEntity.badRequest().body("false");
        }
    }



    @PostMapping("/create/online")
    public ResponseEntity<?> createOrderOnline(@RequestBody OrdersRequest request , HttpServletRequest request1){
        VPResponDto check = orderService.createOrderOnline(request, request1);
        if(check != null){
            return ResponseEntity.ok().body(check);
        }else {
            return ResponseEntity.badRequest().body("false");
        }
    }
}
