package com.martins.compraservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItensCompraDto {
	private Long id;
	private String skuCode;
	private BigDecimal preco;
	private Integer quantidade;
}
