package com.martins.compraservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.martins.compraservice.dto.InventarioResponse;
import com.martins.compraservice.event.CompraEfetuadaEvent;
import com.martins.compraservice.model.ItensCompra;
import com.martins.compraservice.repository.CompraRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.martins.compraservice.dto.CompraRequest;
import com.martins.compraservice.dto.ItensCompraDto;
import com.martins.compraservice.model.Compra;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CompraService {
	
	private final CompraRepository compraRepository;
	private final WebClient.Builder webClientBuilder;
	private final KafkaTemplate<String, CompraEfetuadaEvent> kafkaTemplate;

	public void efetuarPedido(CompraRequest compraRequest) {
		Compra compra = new Compra();
		compra.setNumeroCompra(UUID.randomUUID().toString());
		
		List<ItensCompra> itensCompra = compraRequest.getItensCompraDto().stream()
		.map(this::mapToDto)
		.collect(Collectors.toList());
		
		compra.setItensCompra(itensCompra);

		List<String> skuCodes = compra.getItensCompra().stream().map(ItensCompra::getSkuCode).toList();
		log.info("SkuCodes do Pedido: "+ skuCodes);

		//Chamar Inventario Service
		InventarioResponse[]  InventarioResponses = webClientBuilder.build().get()
				.uri("http://inventario-service/api/inventario",
						uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
				.retrieve()
				.bodyToMono(InventarioResponse[].class)
				.block(); // Requisição Síncrona

		boolean todosOsProdutosEmEstoque = Arrays.stream(InventarioResponses).allMatch(InventarioResponse::isInStock);
		log.info("Todos os produtos existem no estoque: "+ todosOsProdutosEmEstoque);

		if(todosOsProdutosEmEstoque){
			compraRepository.save(compra);
			kafkaTemplate.send("notificationTopic",new CompraEfetuadaEvent(compra.getNumeroCompra()));
		//	return "Compra efetuada com sucesso";
		}else{
			throw new IllegalArgumentException("Produto fora do estoque. Realizar Tentativa mais tarde.");
		}

	}
	
	
	private ItensCompra mapToDto(ItensCompraDto itensCompraDto) {
		
		ItensCompra itensCompra = new ItensCompra();
		
		itensCompra.setPreco(itensCompraDto.getPreco());
		itensCompra.setQuantidade(itensCompraDto.getQuantidade());
		itensCompra.setSkuCode(itensCompraDto.getSkuCode());
		
		return itensCompra;
	}


	 
}
