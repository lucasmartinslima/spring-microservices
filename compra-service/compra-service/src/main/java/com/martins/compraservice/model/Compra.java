package com.martins.compraservice.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="t_compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String numeroCompra;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ItensCompra> itensCompra;

}
