package com.example.word_phone_web.repo;

import com.example.word_phone_web.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity,Long> {
    @Query("select o from CategoryEntity o where o.deleteFlag = false")
    List<CategoryEntity> findByDeleteFlagIsFalse();

    Optional<CategoryEntity> findById(Long id);


/**
 * Description:
 *
 * @author: hieu
 * @since: 12/08/2022
 * Project_name: com.example.word_phone_web.repo
 */

    CategoryEntity findByDeleteFlagIsFalseAndId(Long id);
}

