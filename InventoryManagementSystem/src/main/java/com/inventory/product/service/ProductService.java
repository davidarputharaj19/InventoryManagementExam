package com.inventory.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.product.entity.Product;
import com.inventory.product.repository.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository= productRepository;
	}

	public List<Product> findAllProducts() {		
		return productRepository.findAll();
	}

	public Product findProductById(Long id) {
		return productRepository.findById(id).get();
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public void updateProduct(Long id, Product product) {
		Product productFromDB= productRepository.findById(id).get();
		if(productFromDB == null) {
			//throw exception
		} else {
			if(product==null) {
				//throw exception
			}else {
				if(product.getPrice() != 0L) {
					productFromDB.setPrice(product.getPrice());
					productRepository.save(productFromDB);
				}
			}
			productRepository.save(productFromDB);
		}
		
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	public Double getPrice(Long id) {		
		return productRepository.findById(id).get().getPrice();
	}
	
	public List<Product> saveAllProducts(List<Product> products){
		return productRepository.saveAll(products);
	}

}
