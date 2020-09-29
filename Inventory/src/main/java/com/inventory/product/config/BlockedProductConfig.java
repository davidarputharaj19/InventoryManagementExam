package com.inventory.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:banned-products.properties")
public class BlockedProductConfig {
	
	@Value("${banned.products}")
	private String bannedProducts;

	public String getBannedProducts() {
		return bannedProducts;
	}
	
}
