package com.web.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

	private Long storeId;
	private Long sku;	
	private String productName;
	private Double price;
	
	public StoreDto(Long storeId, String productName, Double price) {
		this.storeId = storeId;
		this.productName = productName;
		this.price = price;
	}
}