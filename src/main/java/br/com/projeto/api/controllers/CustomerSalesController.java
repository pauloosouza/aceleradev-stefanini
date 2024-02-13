package br.com.projeto.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.services.CustomerSalesService;

@RestController
public class CustomerSalesController {

  @Autowired
  private CustomerSalesService customerSalesService;

  @GetMapping("/customer-sales")
  public ResponseEntity<?> index(@RequestParam(required = true) String cpf) {
    return customerSalesService.listSalesToCustomer(cpf);
  }

}
