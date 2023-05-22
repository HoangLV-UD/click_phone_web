package com.example.click_phone_web.repo;

import com.example.click_phone_web.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepo extends JpaRepository<ColorEntity, Long> {

    ColorEntity findByDeleteFlagIsFalseAndId(Long id);
}
