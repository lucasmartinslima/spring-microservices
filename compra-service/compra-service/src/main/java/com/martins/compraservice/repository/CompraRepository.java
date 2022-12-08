package com.martins.compraservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martins.compraservice.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{

}
