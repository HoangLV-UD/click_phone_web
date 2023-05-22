package com.example.click_phone_web.repo;

import com.example.click_phone_web.entity.PinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PinRepo extends JpaRepository<PinEntity, Long> {
    List<PinEntity> findByDeleteFlagIsFalse();
}
