package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.ProductSize;


@Repository
public interface IProductSizeRepository extends JpaRepository<ProductSize, Long> {

}
