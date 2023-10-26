package com.martins.inventarioservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.martins.inventarioservice.model.Inventario;
import com.martins.inventarioservice.repository.InventarioRepository;

@SpringBootApplication
public class InventarioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner carregarDados(InventarioRepository inventarioRepository) {
		
		return args -> {
			Inventario inventario = new Inventario();
			inventario.setSkuCode("iphone_13");
			inventario.setQuantidade(26);
			
			Inventario inventario1 = new Inventario();
			inventario1.setSkuCode("iphone_14_red");
			inventario1.setQuantidade(88);
			
			inventarioRepository.save(inventario);
			inventarioRepository.save(inventario1);
		};
	
	}
	
}
