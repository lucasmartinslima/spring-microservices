package com.martins.produtoservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.martins.produtoservice.dto.ProdutoRequest;
import com.martins.produtoservice.dto.ProdutoResponse;
import com.martins.produtoservice.service.ProdutoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import zipkin2.reporter.Reporter;

@RestController
@RequestMapping("/api/produto")
@RequiredArgsConstructor
@Slf4j
public class ProdutoController {

	private final ProdutoService produtoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void criarProduto(@RequestBody ProdutoRequest produtoRequest) {
		produtoService.criarProduto(produtoRequest);
		log.info("Requisição de criação de produto recebida");

	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProdutoResponse> getAllProdutos(){
		return produtoService.getAllProdutos();
	}
}
