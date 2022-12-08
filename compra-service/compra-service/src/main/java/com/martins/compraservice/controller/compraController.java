package com.martins.compraservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.martins.compraservice.dto.CompraRequest;
import com.martins.compraservice.service.CompraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/compra")
@RequiredArgsConstructor
public class compraController {

	private final CompraService compraService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String efetuarCompra(@RequestBody CompraRequest compraRequest) {
		compraService.efetuarPedido(compraRequest);
		return "Pedido da compra efetuado com sucesso";
		
	}
	
}
