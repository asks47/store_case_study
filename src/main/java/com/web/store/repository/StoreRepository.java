package com.web.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.store.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
