package com.martins.inventarioservice.controller;

import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.martins.inventarioservice.service.InventarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {
	
	
	private final InventarioService inventarioService;
	
	@GetMapping("/{sku-code}")
	@ResponseStatus(HttpStatus.OK)
	public boolean possuiEstoque(@PathVariable("sku-code") String skuCode) {
		return inventarioService.possuiEstoque(skuCode);
	}
}
