package br.com.projeto.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.services.ProductSalesService;

@RestController
@RequestMapping("/products")
public class ProductSalesController {

  @Autowired
  private ProductSalesService productSalesService;

  @GetMapping("/{id}/sales")
  public ResponseEntity<?> show(@PathVariable int id,
      @RequestParam(required = false) String month,
      @RequestParam(required = false) String year) {
    return productSalesService.listSalesToProduct(id, month, year);
  }

}
