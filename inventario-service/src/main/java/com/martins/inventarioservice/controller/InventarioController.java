package com.martins.inventarioservice.controller;

import com.martins.inventarioservice.dto.InventarioResponse;
import com.martins.inventarioservice.service.InventarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {
	
	
	private final InventarioService inventarioService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventarioResponse> possuiEstoque(@RequestParam List<String> skuCode) {
		return inventarioService.possuiEstoque(skuCode);
	}
}
