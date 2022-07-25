package com.example.word_phone_web.repo;

import com.example.word_phone_web.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author: hieu
 * @since: 19/07/2022
 * Project_name: com.example.word_phone_web.repo
 */
@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByEmail(String email);
    CustomerEntity findByPhoneNumber(String phone);
}
