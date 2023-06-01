package com.shop.repository;

import com.shop.entity.CheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckRepository extends JpaRepository<CheckEntity, Long> {
    @Override
    Optional<CheckEntity> findById(Long id);
}
