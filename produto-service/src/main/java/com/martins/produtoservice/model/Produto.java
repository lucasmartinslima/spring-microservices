package com.martins.produtoservice.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Document(value = "produto") 
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class Produto {
	@Id
	private String id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
}
