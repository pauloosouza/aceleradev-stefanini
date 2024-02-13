package br.com.projeto.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.api.models.Product;
import br.com.projeto.api.models.SaleProduct;

import java.time.LocalDate;
import java.util.List;

public interface SaleProductRepository extends JpaRepository<SaleProduct, Integer> {
  List<SaleProduct> findByProduct(Product product);

  List<SaleProduct> findBySaleDateBetweenAndProduct(LocalDate startDate, LocalDate endDate, Product product);
}
