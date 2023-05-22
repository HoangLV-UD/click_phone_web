package com.example.click_phone_web.repo;

import com.example.click_phone_web.entity.ChipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChipRepo extends JpaRepository<ChipEntity, Long> {
    List<ChipEntity> findByDeleteFlagIsFalse();
}
