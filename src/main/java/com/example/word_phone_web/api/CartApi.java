package com.example.word_phone_web.api;

import com.example.word_phone_web.dto.request.cart.CartEditRequest;
import com.example.word_phone_web.dto.request.cart.CartRequest;
import com.example.word_phone_web.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 21/07/2022
 * Project_name: com.example.word_phone_web.api
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartApi {
    private final CartService cartService;

    @PostMapping("")
    public ResponseEntity<?> addCart(@RequestBody CartRequest request){
        String check = cartService.addCart(request);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }
        if(check.equals("false")){
            return ResponseEntity.badRequest().body("false");
        } else{
            return ResponseEntity.badRequest().body("sl");
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
