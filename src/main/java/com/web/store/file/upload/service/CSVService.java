package com.web.store.file.upload.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.web.store.file.upload.utility.CSVHelper;
import com.web.store.model.Product;
import com.web.store.repository.ProductRepository;


public class CSVService {
  
  @Autowired
  ProductRepository prodRepository;

  public void save(MultipartFile file) {
    try {
      //List<Product> products = 
    	CSVHelper.csvToProducts(file.getInputStream());
      //prodRepository.saveAll(products);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<Product> products = prodRepository.findAll();

    ByteArrayInputStream in = CSVHelper.productsToCSV(products);
    return in;
  }

  public List<Product> getAllTutorials() {
    return prodRepository.findAll();
  }
}
