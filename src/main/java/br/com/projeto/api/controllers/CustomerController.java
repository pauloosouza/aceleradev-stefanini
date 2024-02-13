package br.com.projeto.api.controllers;

import br.com.projeto.api.models.Customer;
import br.com.projeto.api.services.CustomerService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping("/customers")
  public List<Customer> index() {
    return customerService.listAll();
  }

  @PostMapping("/customers")
  public ResponseEntity<?> create(@Valid @RequestBody Customer customer) {
    return customerService.create(customer);
  }

  @GetMapping("/customers/{id}")
  public ResponseEntity<?> show(@PathVariable int id) {
    return customerService.show(id);
  }

  @PutMapping("/customers/{id}")
  public ResponseEntity<?> update(@PathVariable int id, @RequestBody Customer customer) {
    return customerService.update(id, customer);
  }

  @DeleteMapping("/customers/{id}")
  public ResponseEntity<?> delete(@PathVariable int id) {
    return customerService.delete(id);
  }
}
