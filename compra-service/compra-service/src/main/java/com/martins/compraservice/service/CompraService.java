package com.martins.compraservice.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.martins.compraservice.dto.CompraRequest;
import com.martins.compraservice.dto.ItensCompraDto;
import com.martins.compraservice.model.Compra;
import com.martins.compraservice.model.ItensCompra;
import com.martins.compraservice.repository.CompraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CompraService {
	
	private final CompraRepository compraRepository;
	
	public void efetuarPedido(CompraRequest compraRequest) {
		Compra compra = new Compra();
		compra.setNumeroCompra(UUID.randomUUID().toString());
		
		List<ItensCompra> itensCompra = compraRequest.getItensCompraDto().stream()
		.map(this::mapToDto)
		.collect(Collectors.toList());
		
		compra.setItensCompra(itensCompra);
		
		compraRepository.save(compra);
	}
	
	
	private ItensCompra mapToDto(ItensCompraDto itensCompraDto) {
		
		ItensCompra itensCompra = new ItensCompra();
		
		itensCompra.setPreco(itensCompraDto.getPreco());
		itensCompra.setQuantidade(itensCompraDto.getQuantidade());
		itensCompra.setSkuCode(itensCompraDto.getSkuCode());
		
		return itensCompra;
	}


	 
}
