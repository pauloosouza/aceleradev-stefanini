package br.com.projeto.api.repositories;

import br.com.projeto.api.models.Customer;
import br.com.projeto.api.models.Sale;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface CustomerSalesRepository extends JpaRepository<Sale, Integer> {
  List<Sale> findByCustomer(Customer customer);
}
