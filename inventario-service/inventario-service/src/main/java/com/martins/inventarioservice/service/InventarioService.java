package com.martins.inventarioservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.martins.inventarioservice.repository.InventarioRepository;
 

@Service 
public class InventarioService {

	@Autowired
	InventarioRepository inventarioRepository;
	
	@Transactional(readOnly = true)
	public boolean possuiEstoque(String skuCode) {
		return inventarioRepository.findBySkuCode(skuCode).isPresent();
	}
}
