package com.web.store.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long storeId;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="store_id", referencedColumnName = "storeId")
	private List<Product> productList;
	
	public Store(Long storeId,String productName,Double price) {
		this.storeId = storeId;
	}
	public Store(Long storeId) {
		this.storeId = storeId;
	}
	public Store() {
		
	}
	public void setStoreId(Long long1) {
		// TODO Auto-generated method stub
		this.storeId = storeId;
		
	}
}