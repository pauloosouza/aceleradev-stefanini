package br.com.projeto.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.models.Product;
import br.com.projeto.api.repositories.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public ResponseEntity<?> create(Product product) {
    boolean productExists = productRepository.existsByCode(product.getCode());

    if (productExists)
      return new ResponseEntity<>("Produto já cadastrado", HttpStatus.BAD_REQUEST);

    return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
  }

  public List<Product> listAll() {
    return productRepository.findAll();
  }

  public ResponseEntity<?> show(int id) {
    Product product = productRepository.findById(id);

    if (product == null)
      return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  public ResponseEntity<?> update(int id, Product product) {
    Product productExists = productRepository.findById(id);

    if (productExists == null)
      return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_FOUND);

    if (product.getName() != null)
      productExists.setName(product.getName());

    if (product.getCode() != null)
      productExists.setCode(product.getCode());

    if (product.getPrice() != null)
      productExists.setPrice(product.getPrice());

    return new ResponseEntity<>(productRepository.save(productExists), HttpStatus.OK);
  }

  public ResponseEntity<?> delete(int id) {
    Product product = productRepository.findById(id);

    if (product == null)
      return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_FOUND);

    productRepository.delete(product);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public Product searchProductByCode(String code) {
    return productRepository.findByCode(code);
  }
}
