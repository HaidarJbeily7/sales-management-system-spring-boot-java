package com.example.demo;

import com.example.demo.categories.Category;
import com.example.demo.clients.Client;
import com.example.demo.products.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class DemoApplicationTests {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	List<Client> clients = null;
	List<Product> products = null;

	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext,
					  RestDocumentationContextProvider restDocumentation) {

		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation))
				.build();

		clients = Stream.of(new Client("Haidar1", "Jbeily1", "+963997610306"),
						new Client("Haidar2", "Jbeily2", "+963997610800"))
				.collect(Collectors.toList());
		Category cat_1 = new Category(1L,"title1", "desc1");
		Category cat_2 = new Category(2L,"title2", "desc2");
		products = Stream.of(new Product("product1", "desc1", cat_1),
						new Product("Haidar2", "Jbeily2", cat_2))
				.collect(Collectors.toList());
	}

	@Test
	public void testAddClient() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		String clientJson = mapper.writeValueAsString(clients.get(0));
		mockMvc.perform(post("/api/v1/clients")
						.content(clientJson)
						.contentType("application/json")).andDo(print())
				.andExpect(status().isCreated())
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));
	}

	@Test
	public void testGetClients() throws Exception {

		mockMvc.perform(get("/api/v1/clients/0/2")
						.contentType("application/json")).andDo(print())
				.andExpect(status().isOk())
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));
	}

	@Test
	public void testEditClient() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		String clientJson = mapper.writeValueAsString(clients.get(1));
		mockMvc.perform(put("/api/v1/clients/1")
						.content(clientJson)
						.contentType("application/json")).andDo(print())
				.andExpect(status().isOk())
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));
	}

	@Test
	public void testAddProduct() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		String productJson = mapper.writeValueAsString(products.get(0));
		mockMvc.perform(post("/api/v1/products")
						.content(productJson)
						.contentType("application/json")).andDo(print())
				.andExpect(status().isCreated())
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));
	}

	@Test
	public void testGetproducts() throws Exception {

		mockMvc.perform(get("/api/v1/products/0/2")
						.contentType("application/json")).andDo(print())
				.andExpect(status().isOk())
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));
	}

	@Test
	public void testEditProduct() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		String productJson = mapper.writeValueAsString(products.get(1));
		mockMvc.perform(put("/api/v1/products/1")
						.content(productJson)
						.contentType("application/json")).andDo(print())
				.andExpect(status().isOk())
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));
	}

	@Test
	public void testAddSale() throws Exception {

		String saleJson = "{\"client\":{\"id\": 1},\n" +
				"\"seller\":{\"id\": 1},\n" +
				"\"sale_transactions\": [\n" +
				"{\"product\":{\"id\": 1},\"quantity\": 1,\"unit_price\": 10}]}\n";
		mockMvc.perform(post("/api/v1/sales")
						.content(saleJson)
						.contentType("application/json")).andDo(print())
				.andExpect(status().isCreated())
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));
	}

	@Test
	public void testGetSales() throws Exception {
		mockMvc.perform(get("/api/v1/sales/0/2")
						.contentType("application/json")).andDo(print())
				.andExpect(status().isOk())
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));
	}

	@Test
	public void testEditSaleTransactions() throws Exception {

		String transactionsJson = "[{\"product\":{\"id\": 1},\t\"quantity\": 11,\t\"unit_price\": 10}]";
		mockMvc.perform(patch("/api/v1/sales/2")
						.content(transactionsJson)
						.contentType("application/json")).andDo(print())
				.andExpect(status().isOk())
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));
	}
}
