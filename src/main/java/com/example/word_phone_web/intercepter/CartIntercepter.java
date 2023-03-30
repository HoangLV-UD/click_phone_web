package com.example.word_phone_web.intercepter;

import com.example.word_phone_web.dto.respone.cart.CartRespone;
import com.example.word_phone_web.entity.CartEntity;
import com.example.word_phone_web.entity.CustomerEntity;
import com.example.word_phone_web.repo.CartRepo;
import com.example.word_phone_web.repo.CustomerRepo;
import com.example.word_phone_web.service.CartService;
import com.example.word_phone_web.service.CategoryService;
import com.example.word_phone_web.util.ConvertUtil;
import com.example.word_phone_web.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 06/08/2022
 * Project_name: com.example.word_phone_web.intercepter
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class CartIntercepter implements HandlerInterceptor {
    private final SessionUtil sessionUtil;

    private final CartService cartService;

    private final ConvertUtil convertUtil;

    private final CategoryService categoryService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long totalCart = 0;
        int sizeCart = 0;
        if(String.valueOf(sessionUtil.getObject("username")) != "null"){
            List<CartRespone> listCart = cartService.findByCustomer();
            sizeCart = listCart.size();
            for (CartRespone respone: listCart
                 ) {
                totalCart+= respone.getTotal();
            }
        }
        request.setAttribute("sizeCart", sizeCart);
        request.setAttribute("category", categoryService.findByCategoryAndDeleteFlagIsFalse());
        request.setAttribute("totalCart", convertUtil.moneyToStringFormat(totalCart));
        request.setAttribute("totalCartPrice", totalCart);
        return true;
    }
}
