package br.com.projeto.api.repositories;

import br.com.projeto.api.models.Customer;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  Customer findById(int id);

  boolean existsByCpf(String cpf);

  Customer findByCpf(String cpf);
}
