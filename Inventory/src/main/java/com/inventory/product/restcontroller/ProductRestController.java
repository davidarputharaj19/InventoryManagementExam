package com.inventory.product.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.CacheControl;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inventory.product.entity.Product;
import com.inventory.product.exception.BlockedProductException;
import com.inventory.product.exception.ResourceNotFoundException;
import com.inventory.product.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductRestController {

	private ProductService productService;
	
	Logger logger = LoggerFactory.getLogger(ProductRestController.class);

	
	@Autowired
	public ProductRestController(ProductService productService){
		this.productService = productService;
	}
	
	@GetMapping("/products")
	public List<Product> findAllProducts(){
		logger.info("Inside findAllProducts() method");
		return productService.findAllProducts();
	}
	
	@GetMapping("/products/{id}")
	public Product findProductById(@PathVariable Long id) throws ResourceNotFoundException{
		logger.info("Inside findProductById() method");
		return productService.findProductById(id);
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) throws BlockedProductException {
		logger.info("Inside saveProduct() method");
		product= productService.saveProduct(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
		return ResponseEntity.created(location).body(product);
	}
	
	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product product) throws ResourceNotFoundException {
		logger.info("Inside updateProduct() method");
		return productService.updateProduct(id, product);
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable Long id) throws ResourceNotFoundException{
		productService.deleteProduct(id);
	}
	
	@GetMapping("/products/price/{id}")
	@Cacheable("price")
	public ResponseEntity<Double> getPrice(@PathVariable Long id) throws ResourceNotFoundException{
		logger.info("Inside getPrice() method");
		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES)
			      .noTransform()
			      .mustRevalidate();
		return ResponseEntity.ok().cacheControl(cacheControl).body(productService.getPrice(id));
	}
	
	@GetMapping("/seller/{id}")
	public ResponseEntity<Product> getProductForSeller(@PathVariable Long id) throws ResourceNotFoundException {
		logger.info("Inside getProductForSeller() method");
		if(id == 1) {
			return ResponseEntity.status(HttpStatus.FOUND).build();
		} else {
			return ResponseEntity.ok(productService.findProductById(id));
		}
	}

}
