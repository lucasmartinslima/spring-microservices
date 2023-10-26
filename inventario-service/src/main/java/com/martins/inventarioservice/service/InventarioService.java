package com.martins.inventarioservice.service;

import com.martins.inventarioservice.dto.InventarioResponse;
import com.martins.inventarioservice.repository.InventarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class InventarioService {

	@Autowired
	InventarioRepository inventarioRepository;
	
	@Transactional(readOnly = true)
	public List<InventarioResponse> possuiEstoque(List<String> skuCode) {

		List<InventarioResponse> possuiEstoque = inventarioRepository.findBySkuCodeIn(skuCode).stream()
				.map(inventario ->
						InventarioResponse.builder()
								.skuCode(inventario.getSkuCode())
								.isInStock(inventario.getQuantidade() > 0)
								.build()
				).collect(Collectors.toList());

		return possuiEstoque;
	}
}
