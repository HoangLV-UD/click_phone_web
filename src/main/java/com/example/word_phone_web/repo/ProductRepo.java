package com.example.word_phone_web.repo;


import com.example.word_phone_web.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

    @Query("select o from ProductEntity o where o.deleteFlag = false  and o.status = 'ON' and o.id = ?1")
    Optional<ProductEntity> findByIdProduct(Long id);
}
