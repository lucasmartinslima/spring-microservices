package com.martins.inventarioservice.repository;
 

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martins.inventarioservice.model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

	Optional<Inventario> findBySkuCode(String skuCode);

}
