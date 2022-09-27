package com.example.word_phone_web.repo;

import com.example.word_phone_web.entity.CustomerEntity;
import com.example.word_phone_web.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 12/08/2022
 * Project_name: com.example.word_phone_web.repo
 */
@Repository
public interface OrdersRepo extends JpaRepository<OrdersEntity, Long> {
    List<OrdersEntity> findByCustomerEntityOrderByCreateDateDesc(CustomerEntity entity);

    OrdersEntity findByCodeOrder(String code);
}
