package com.martins.discoveryserver.produtoservice;

import java.math.BigDecimal;

import com.martins.produtoservice.dto.ProdutoRequest;
import com.martins.produtoservice.repository.ProdutoRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static  org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.assertions.Assertions;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdutoServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2.2");
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	ProdutoRepository produtorepository;
	
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	
	@Test
	@Order(1) 
	void deveCriarProduto() throws Exception {
		ProdutoRequest produtoRequest = getProdutoRequest();
	
		String produtoRequestString = objectMapper.writeValueAsString(produtoRequest);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/produto")
				.contentType(MediaType.APPLICATION_JSON)
				.content(produtoRequestString))
			.andExpect(status().isCreated());
		Assertions.assertTrue(produtorepository.findAll().size() == 1);
	}
	
	private ProdutoRequest getProdutoRequest() {
		return ProdutoRequest.builder()
				.nome("Iphone 14 Pro Max")
				.descricao("Iphone 14 Pro Max - Branco")
				.preco(BigDecimal.valueOf(8500))
				.build();
	}
	
	@Test
	@Order(2) 
	void deveRetornarTodosOsProduto() throws Exception {
		
	MvcResult mockResult =	mockMvc.perform(MockMvcRequestBuilders.get("/api/produto")
				.accept(MediaType.APPLICATION_JSON)) 
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isOk())
			.andReturn();
	
	String resultPegarTodosOsProdutos = mockResult.getResponse().getContentAsString();
	 
	Assertions.assertTrue(resultPegarTodosOsProdutos.startsWith("[{")); 
	}
}
