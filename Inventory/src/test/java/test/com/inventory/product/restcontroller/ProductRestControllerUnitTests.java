package test.com.inventory.product.restcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.inventory.product.App;
import com.inventory.product.entity.Product;
import com.inventory.product.restcontroller.ProductRestController;
import com.inventory.product.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { App.class })
@WebAppConfiguration
public class ProductRestControllerUnitTests {

	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	ProductRestController productRestController;

	@MockBean
	private ProductService productService;

	Gson gson;

	Logger logger = LoggerFactory.getLogger(ProductRestControllerUnitTests.class);

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		productRestController = new ProductRestController(productService);
		gson = new Gson();
	}

	@Test
	public void getAllProductsTest() {
		try {
			List<Product> mockedProducts = Arrays.asList(new Product("Test name", "Test description", 100, 100));
			when(productService.findAllProducts()).thenReturn(mockedProducts);
			MockHttpServletResponse response = mockMvc.perform(get("/api/products"))
					.andExpect(status().isOk()).andReturn().getResponse();
			assertEquals(gson.toJson(mockedProducts), response.getContentAsString());
		} catch (Exception e) {
			logger.error("getAllProductsTest test case failed " + e.getMessage());
		}
	}

	@Test
	public void getOneProductTest() {
		try {
			Product mockedProduct = new Product("Test name", "Test description", 100, 100);
			when(productService.findProductById(Mockito.anyLong())).thenReturn(mockedProduct);
			MockHttpServletResponse response = mockMvc.perform(get("/api/products/0"))
					.andExpect(status().isOk()).andReturn().getResponse();
			assertEquals(gson.toJson(mockedProduct), response.getContentAsString());
		} catch (Exception e) {
			logger.error("getOneProductTest test case failed " + e.getMessage());
		}
	}

	@Test
	public void productPriceTest() {
		try {
			Double price = 0.0;
			when(productService.getPrice(any(Long.class))).thenReturn(price);
			MockHttpServletResponse response = mockMvc.perform(get("/api/products/price/" +0))
					.andExpect(status().isOk()).andReturn().getResponse();

			assertEquals(price, Double.valueOf(response.getContentAsString()));
		} catch (Exception e) {
			logger.error("productPriceTest test case failed " + e.getMessage());
		}
	}

	@Test
	public void sellerProductTestId1() {
		try {
			long id1 = 1;
			Product mockedProduct = new Product("Test name", "Test description", 100, 100);
			when(productService.findProductById(any(Long.class))).thenReturn(mockedProduct);
			mockMvc.perform(get("/api/seller/" + id1))
					.andExpect(status().isFound()).andReturn().getResponse();			
		} catch (Exception e) {
			logger.error("sellerProductTestId1 test case failed " + e.getMessage());
		}
	}
	
	@Test
	public void sellerProductTestId2() {
		try {
			long id2 = 2;
			Product mockedProduct = new Product("Test name", "Test description", 100, 100);
			when(productService.findProductById(any(Long.class))).thenReturn(mockedProduct);
			MockHttpServletResponse response2 = mockMvc.perform(get("/api/seller/" + id2))
					.andExpect(status().isOk()).andReturn().getResponse();
			assertEquals(gson.toJson(mockedProduct), response2.getContentAsString());
		} catch (Exception e) {
			logger.error("sellerProductTestId2 test case failed " + e.getMessage());
		}
	}

}
