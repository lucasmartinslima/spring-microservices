package com.martins.produtoservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.martins.produtoservice.model.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String>{

}
