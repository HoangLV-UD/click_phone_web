package com.example.word_phone_web.repo;

import com.example.word_phone_web.entity.ProductPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPropertyRepo extends JpaRepository<ProductPropertyEntity, Long> {

    @Query("select o from ProductPropertyEntity o where o.deleteFlag = false and o.status = 'ON' and o.romId = ?1")
    List<ProductPropertyEntity> findByRomId(Long id);
}
