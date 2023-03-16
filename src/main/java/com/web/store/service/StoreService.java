package com.web.store.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.store.model.Product;

@Service
public interface StoreService {

	public List<Product> getAllProduct();
	public List<Product> getProductById();
	public void removeProduct(Product product);
	public List<Product> addProduct(List<Product> products);
}
