package com.example.word_phone_web.repo;


import com.example.word_phone_web.entity.RomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RomRepo extends JpaRepository<RomEntity, Long> {

    @Query("select o from RomEntity o where o.productId = ?1")
    List<RomEntity> findByProductId(Long id);
}
