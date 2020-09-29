package test.com.inventory.product.restcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.inventory.product.App;
import com.inventory.product.entity.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductRestControllerIntegrationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();


	@Test
	public void getAllProducts() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Product[]> response = restTemplate.exchange(createURLWithPort("/api/products"), HttpMethod.GET,
				entity, Product[].class);		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCodeValue());
	}

	@Test
	public void getOneProduct() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Product> response = restTemplate.exchange(createURLWithPort("/api/products/1"), HttpMethod.GET,
				entity, Product.class);
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		assertEquals(1, response.getBody().getId());
		assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCodeValue());
	}

	@Test
	public void getPrice() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Double> response = restTemplate.exchange(createURLWithPort("/api/products/price/1"),
				HttpMethod.GET, entity, Double.class);
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCodeValue());
	}

	@Test
	public void getSellerProductForId1() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Product> response = restTemplate.exchange(createURLWithPort("/api/seller/1"), HttpMethod.GET,
				entity, Product.class);
		assertEquals(HttpStatus.FOUND.value(), response.getStatusCodeValue());
		assertEquals(null, response.getBody());
		assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCodeValue());
	}

	@Test
	public void getSellerProductForIdNot1() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Product> response = restTemplate.exchange(createURLWithPort("/api/seller/2"), HttpMethod.GET,
				entity, Product.class);
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		assertEquals(2, response.getBody().getId());
		assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCodeValue());
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	

}
