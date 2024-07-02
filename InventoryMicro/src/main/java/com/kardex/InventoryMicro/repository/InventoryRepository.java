package com.kardex.InventoryMicro.repository;

import com.kardex.InventoryMicro.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Serializable> {
    Optional<InventoryEntity> findTopByOrderByIdDesc();
}
