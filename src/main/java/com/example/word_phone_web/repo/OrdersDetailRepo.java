package com.example.word_phone_web.repo;

import com.example.word_phone_web.entity.OrdersDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 *
 * @author: hieu
 * @since: 13/08/2022
 * Project_name: com.example.word_phone_web.repo
 */
public interface OrdersDetailRepo extends JpaRepository<OrdersDetailEntity, Long> {
}
