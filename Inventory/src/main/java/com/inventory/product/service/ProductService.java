package com.inventory.product.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.product.config.BlockedProductConfig;
import com.inventory.product.entity.Product;
import com.inventory.product.exception.BlockedProductException;
import com.inventory.product.exception.ResourceNotFoundException;
import com.inventory.product.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;

	private BlockedProductConfig blockedProductConfig;

	@Autowired
	public ProductService(ProductRepository productRepository, BlockedProductConfig blockedProductConfig) {
		this.productRepository = productRepository;
		this.blockedProductConfig = blockedProductConfig;
	}
	
	@Transactional
	public Product findProductById(Long id) throws ResourceNotFoundException {
		return productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Not Found", "Resource Not Found", LocalDateTime.now()));
	}

	@Transactional
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	@Transactional
	public Product saveProduct(Product product) throws BlockedProductException {
		List<String> blockedProductsArray = Arrays.asList(blockedProductConfig.getBannedProducts().split(","));
		if (blockedProductsArray.stream()
				.anyMatch(blockedProduct -> blockedProduct.equalsIgnoreCase(product.getName()))) {
			throw new BlockedProductException("Product Not Allowed", "Product is blocked and cannot be added",
					LocalDateTime.now());
		} else {
			return productRepository.save(product);
		}
	}

	@Transactional
	public List<Product> saveAllProducts(List<Product> products) {
		return productRepository.saveAll(products);
	}

	@Transactional
	public Product updateProduct(Long id, Product product) throws ResourceNotFoundException {
		Product productFromDB = productRepository.findById(id).get();
		if (productFromDB == null) {
			throw new ResourceNotFoundException("Not Found", "Resource Not Found", LocalDateTime.now());
		} else {
			if (product == null) {
				throw new RuntimeException("Product cannot be null");
			} else {
				if (product.getName() != null) {
					productFromDB.setName(product.getName());
				} else if (product.getDescription() != null) {
					productFromDB.setDescription(product.getDescription());
				} else if (product.getPrice() != 0L) {
					productFromDB.setPrice(product.getPrice());
				} else if (product.getQuantity() != 0L) {
					productFromDB.setQuantity(product.getQuantity());
				}
			}
			return productRepository.save(productFromDB);
		}

	}

	@Transactional
	public void deleteProduct(Long id) throws ResourceNotFoundException {
		productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Not Found", "Resource Not Found", LocalDateTime.now()));
		productRepository.deleteById(id);
	}
	
	@Transactional
	public Double getPrice(Long id) throws ResourceNotFoundException {
		return productRepository.findById(id)
				.orElseThrow(
						() -> new ResourceNotFoundException("Not Found", "Resource Not Found", LocalDateTime.now()))
				.getPrice();
	}

	@Transactional
	public void deleteAll() {
		productRepository.deleteAll();
	}
}
