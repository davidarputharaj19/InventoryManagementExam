package com.inventory.product.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inventory.product.domain.ProductClient;

@Service
public class InventoryUIService {

	private RestTemplate restTemplate;
	
	@Autowired
	public InventoryUIService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<ProductClient> getProducts() {
		ResponseEntity<ProductClient[]> productClients = restTemplate.exchange("http://localhost:9090/api/products", HttpMethod.GET,
				null, ProductClient[].class);
		return Arrays.asList(productClients.getBody());
	}

}
