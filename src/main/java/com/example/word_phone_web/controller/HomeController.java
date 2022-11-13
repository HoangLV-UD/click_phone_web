package com.example.word_phone_web.controller;

import com.example.word_phone_web.dto.respone.product.NewProductRespone;
import com.example.word_phone_web.entity.CategoryEntity;
import com.example.word_phone_web.service.CategoryService;
import com.example.word_phone_web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String getProductByName(Model model) {
        List<CategoryEntity> listCTs = categoryService.findByCategoryAndDeleteFlagIsFalse();
        if (listCTs != null) {
            for (var categori : listCTs
            ) {
                List<NewProductRespone> listIps = productService.findByCateId(categori.getId());
                model.addAttribute("sp" + categori.getId(), listIps);
            }
        }
        return "views/home/index-2";
    }
    @GetMapping("{id}")
    public String getProductByCategori(Model model, @PathVariable("id") String id) {
        List<NewProductRespone> listIps = productService.findByCateId(Long.parseLong(id));
        model.addAttribute("products", listIps);
        return "views/home/categori";
    }
}
