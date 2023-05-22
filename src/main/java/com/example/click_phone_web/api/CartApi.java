package com.example.click_phone_web.api;

import com.example.click_phone_web.controller.LoginController;
import com.example.click_phone_web.dto.request.cart.CartEditRequest;
import com.example.click_phone_web.dto.request.cart.CartRequest;
import com.example.click_phone_web.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartApi {
    private final CartService cartService;

    @Autowired
    LoginController login;

    @PostMapping("")
    public ResponseEntity<?> addCart(@RequestBody CartRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String check = cartService.addCart(request);
            if(check.equals("ok")){
                return ResponseEntity.ok().body("ok");
            }
            if(check.equals("false")){
                return ResponseEntity.badRequest().body("false");
            } else{
                return ResponseEntity.badRequest().body(check);
            }
        } else {

            return ResponseEntity.badRequest().body("Vui lòng đăng nhập");
        }


    }

    @PutMapping("")
    public ResponseEntity<?> editCart(@RequestBody List<CartEditRequest> request){
        String check = cartService.updateCart(request);
        if(check.equals("false")){
            return ResponseEntity.badRequest().body("false");
        }
        return ResponseEntity.ok().body(check);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        String check = cartService.deleteCart(id);
        if(check.equals("false")){
            return ResponseEntity.badRequest().body("false");
        }
        return ResponseEntity.ok().body("ok");

    }
}
