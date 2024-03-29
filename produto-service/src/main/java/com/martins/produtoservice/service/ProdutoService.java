package com.martins.produtoservice.service;

import java.util.List;
import java.util.stream.Collectors;

import com.martins.produtoservice.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import com.martins.produtoservice.dto.ProdutoRequest;
import com.martins.produtoservice.dto.ProdutoResponse;
import com.martins.produtoservice.model.Produto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; 

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutoService {
	 
	private final ProdutoRepository produtoRepository;
	
	public void criarProduto(ProdutoRequest produtoRequest) {
		Produto produto = Produto.builder()
				.nome(produtoRequest.getNome())
				.descricao(produtoRequest.getDescricao())
				.preco(produtoRequest.getPreco())
				.build();
		
		produtoRepository.save(produto);
		log.info("Produto salvo Id:{}, Nome:{}, Preço:{}, Descrição:{}"
				,produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao());
	}

	public List<ProdutoResponse> getAllProdutos() {
		return produtoRepository.findAll()
				.stream()
				.map(ProdutoService::mapToProdutoResponse)
				.collect(Collectors.toList());
	}
	
	private static ProdutoResponse mapToProdutoResponse(Produto produto){
		return ProdutoResponse.builder()
				.id(produto.getId())
				.nome(produto.getNome())
				.descricao(produto.getDescricao())
				.preco(produto.getPreco())
				.build();
	}
}
