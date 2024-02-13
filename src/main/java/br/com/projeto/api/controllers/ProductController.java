package br.com.projeto.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.models.Product;
import br.com.projeto.api.services.ProductService;
import jakarta.validation.Valid;

@RestController
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/products")
  public List<Product> index() {
    return productService.listAll();
  }

  @PostMapping("/products")
  public ResponseEntity<?> create(@Valid @RequestBody Product product) {
    return productService.create(product);
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<?> show(@PathVariable int id) {
    return productService.show(id);
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<?> update(@PathVariable int id, @RequestBody Product product) {
    return productService.update(id, product);
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity<?> delete(@PathVariable int id) {
    return productService.delete(id);
  }
}
