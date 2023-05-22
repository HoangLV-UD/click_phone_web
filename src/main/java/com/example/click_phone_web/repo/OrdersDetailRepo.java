package com.example.click_phone_web.repo;

import com.example.click_phone_web.entity.OrdersDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDetailRepo extends JpaRepository<OrdersDetailEntity, Long> {
    @Query("select o from OrdersDetailEntity o where o.deleteFlag = false and o.ordersEntity.id = ?1")
    List<OrdersDetailEntity> findByDeleteFlagIsFalseAndOrdersEntity(Long id);
}
