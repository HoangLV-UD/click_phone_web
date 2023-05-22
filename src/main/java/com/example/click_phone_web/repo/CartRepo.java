package com.example.click_phone_web.repo;

import com.example.click_phone_web.entity.CartEntity;
import com.example.click_phone_web.entity.ProductPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartRepo extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findByDeleteFlagIsFalseAndIdCustomer(Long id);

    CartEntity findByDeleteFlagIsFalseAndIdCustomerAndIdProduct(Long id, ProductPropertyEntity propertyEntity);

    CartEntity findByDeleteFlagIsFalseAndId(Long id);
}
