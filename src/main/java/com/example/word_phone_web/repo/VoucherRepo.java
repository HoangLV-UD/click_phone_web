package com.example.word_phone_web.repo;

import com.example.word_phone_web.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VoucherRepo extends JpaRepository<VoucherEntity, String> {

    List<VoucherEntity> findAllByCodeAndDeleteFlagIsFalse(String voucherCode);

    List<VoucherEntity> findAllByDeleteFlagIsFalse();

    VoucherEntity getByIdAndDeleteFlagIsFalse(String id);

    @Query("select o from VoucherEntity o where o.deleteFlag = false and o.quantity > 0 and o.status = 'ON' and o.code = ?1")
    VoucherEntity findByDeleteFlagIsFalseAndCode(String code);
}
