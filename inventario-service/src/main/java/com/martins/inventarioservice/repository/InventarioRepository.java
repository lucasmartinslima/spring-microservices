package com.martins.inventarioservice.repository;
 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martins.inventarioservice.model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

	List<Inventario> findBySkuCodeIn(List<String> skuCode);

}
