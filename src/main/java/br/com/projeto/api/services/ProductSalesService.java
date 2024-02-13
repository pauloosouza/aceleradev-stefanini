package br.com.projeto.api.services;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.dtos.ProductSalesDto;
import br.com.projeto.api.models.Product;
import br.com.projeto.api.models.Sale;
import br.com.projeto.api.repositories.ProductRepository;
import br.com.projeto.api.repositories.SaleProductRepository;
import br.com.projeto.api.repositories.SaleRepository;

@Service
public class ProductSalesService {

  @Autowired
  private SaleProductRepository saleProductRepository;

  @Autowired
  private ProductRepository productRepository;

  public ResponseEntity<?> listSalesToProduct(int id, String month, String year) {
    Product product = productRepository.findById(id);

    if (product == null) {
      return ResponseEntity.notFound().build();
    }

    if (month == null || year == null) {
      int salesCount = saleProductRepository.findByProduct(product).size();

      ProductSalesDto productSalesDto = new ProductSalesDto(
          product.getId(),
          product.getCode(),
          product.getName(),
          product.getPrice(),
          salesCount);

      return ResponseEntity.ok(productSalesDto);
    }

    YearMonth yearMonth = YearMonth.of(Integer.parseInt(year), Integer.parseInt(month));
    LocalDate startDate = yearMonth.atDay(1);
    LocalDate endDate = yearMonth.atEndOfMonth();

    int salesCount = saleProductRepository.findBySaleDateBetweenAndProduct(startDate, endDate, product).size();

    ProductSalesDto productSalesDto = new ProductSalesDto(
        product.getId(),
        product.getCode(),
        product.getName(),
        product.getPrice(),
        salesCount);

    return ResponseEntity.ok(productSalesDto);
  }

}
