package br.com.projeto.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.api.models.Sale;
import br.com.projeto.api.repositories.SaleRepository;

@Service
public class SaleService {

  @Autowired
  private SaleRepository saleRepository;

  public Sale create(Sale sale) {
    return saleRepository.save(sale);
  }

}
