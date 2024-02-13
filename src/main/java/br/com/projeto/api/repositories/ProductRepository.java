package br.com.projeto.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.api.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
  Product findById(int id);

  boolean existsByCode(String code);

  Product findByCode(String code);
}
