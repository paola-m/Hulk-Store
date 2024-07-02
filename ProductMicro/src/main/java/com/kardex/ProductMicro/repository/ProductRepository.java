package com.kardex.ProductMicro.repository;

import com.kardex.ProductMicro.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Serializable> {
    Optional<ProductEntity> findTopByOrderByIdProductDesc();
}
