package com.inventory.product.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.product.entity.Product;
import com.inventory.product.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductRestController {

	private ProductService productService;
	
	@Autowired
	public ProductRestController(ProductService productService){
		this.productService = productService;
	}
	
	@GetMapping("products")
	public List<Product> findAllProducts(){
		return productService.findAllProducts();
	}
	
	@GetMapping("products/{id}")
	public Product findProductById(@PathVariable Long id){
		return productService.findProductById(id);
	}
	
	@PostMapping("/products")
	public Product saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}
	
	@PutMapping("/products/{id}")
	public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
		productService.updateProduct(id, product);
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}
	
	@GetMapping("/products/price/{id}")
	@Cacheable("price")
	public Double getPrice(@PathVariable Long id) {
		return productService.getPrice(id);
	}
	
	@GetMapping("/seller/{id}")
	public ResponseEntity<Product> getProductForSeller(@PathVariable Long id) {
		if(id == 1) {
			return ResponseEntity.status(HttpStatus.FOUND).build();
		} else {
			return ResponseEntity.ok(productService.findProductById(id));
		}
	}

}
