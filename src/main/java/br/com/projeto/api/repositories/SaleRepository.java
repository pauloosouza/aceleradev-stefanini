package br.com.projeto.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.api.models.Sale;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

}
