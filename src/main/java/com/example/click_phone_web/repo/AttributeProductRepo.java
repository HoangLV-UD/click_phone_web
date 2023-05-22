package com.example.click_phone_web.repo;

import com.example.click_phone_web.entity.AttributeProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeProductRepo extends JpaRepository<AttributeProductEntity, Long> {
    AttributeProductEntity findByProductId(Long productId);
}
