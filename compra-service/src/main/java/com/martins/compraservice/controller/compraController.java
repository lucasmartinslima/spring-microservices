package com.martins.compraservice.controller;

import com.martins.compraservice.dto.CompraRequest;
import com.martins.compraservice.service.CompraService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/compra")
@RequiredArgsConstructor
public class compraController {

	private final CompraService compraService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CircuitBreaker(name = "inventario",  fallbackMethod = "fallbackMethod")
	public String efetuarCompra(@RequestBody CompraRequest compraRequest) {
		compraService.efetuarPedido(compraRequest);
		return "Pedido da compra efetuado com sucesso";
	}

	public String fallbackMethod(CompraRequest compraRequest, RuntimeException runtimeException){
		return "Erro ao chamar inventário, favor efetuar compra mais tarde.";
	}
	
}
