package com.example.demo;

import com.example.demo.clients.Client;
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class DemoApplicationTests {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	List<Client> clients = null;

	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext,
					  RestDocumentationContextProvider restDocumentation) {

		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation))
				.build();

		clients = Stream.of(new Client("Haidar1", "Jbeily1", "+963997610306"))
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
				.andExpect(status().isOk())
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));
	}

}
