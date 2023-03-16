package com.web.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.store.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	
}
