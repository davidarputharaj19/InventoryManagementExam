package com.inventory.product.component;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.inventory.product.entity.Product;
import com.inventory.product.service.ProductService;

@Component
public class DataLoader implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(DataLoader.class);

	private ProductService productService;

	@Autowired
	public DataLoader(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public void run(String... args) throws Exception {
		productService.deleteAll();
		logger.info("Loading mocked data into database");
		List<Product> products = Arrays.asList(new Product("Java 7 Book", "Learn Java 7 using this book", 250, 1000),
				new Product("Java 8 Book", "Learn Java 8 using this book", 300, 1000),
				new Product("Java 9 Book", "Learn Java 9 using this book", 350, 1000),
				new Product("Java 10 Book", "Learn Java 10 using this book", 400, 1000),
				new Product("Java 11 Book", "Learn Java 11 using this book", 450, 1000));
		productService.saveAllProducts(products);
	}

}
