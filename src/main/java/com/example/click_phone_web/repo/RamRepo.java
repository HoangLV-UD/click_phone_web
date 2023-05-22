package com.example.click_phone_web.repo;

import com.example.click_phone_web.entity.RamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RamRepo extends JpaRepository<RamEntity, Long> {

    List<RamEntity> findByDeleteFlagIsFalse();
}
