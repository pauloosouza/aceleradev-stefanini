package br.com.projeto.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.projeto.api.models.Customer;
import br.com.projeto.api.repositories.CustomerRepository;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  public ResponseEntity<?> create(Customer customer) {
    CPFValidator cpfValidator = new CPFValidator();

    List<ValidationMessage> errors = cpfValidator.invalidMessagesFor(customer.getCpf());

    if (errors.size() > 0)
      return new ResponseEntity<>("CPF inválido", HttpStatus.BAD_REQUEST);

    boolean customerExists = customerRepository.existsByCpf(customer.getCpf());

    if (customerExists)
      return new ResponseEntity<>("Cliente já cadastrado", HttpStatus.BAD_REQUEST);

    return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.CREATED);
  }

  public List<Customer> listAll() {
    return customerRepository.findAll();
  }

  public ResponseEntity<?> show(int id) {
    Customer customer = customerRepository.findById(id);

    if (customer == null)
      return new ResponseEntity<>("Cliente não encontrado", HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(customer, HttpStatus.OK);
  }

  public ResponseEntity<?> update(int id, Customer customer) {
    Customer customerExists = customerRepository.findById(id);

    if (customerExists == null)
      return new ResponseEntity<>("Cliente não encontrado", HttpStatus.NOT_FOUND);

    if (customer.getCpf() != null) {
      CPFValidator cpfValidator = new CPFValidator();

      List<ValidationMessage> errors = cpfValidator.invalidMessagesFor(customer.getCpf());

      if (errors.size() > 0)
        return new ResponseEntity<>("CPF inválido", HttpStatus.BAD_REQUEST);
    }

    boolean cpfAlreadyRegistered = customerRepository.existsByCpf(customer.getCpf());

    if (cpfAlreadyRegistered)
      return new ResponseEntity<>("CPF já utilizado", HttpStatus.BAD_REQUEST);

    customer.setId(id);

    if (customer.getName() != null)
      customerExists.setName(customer.getName());

    if (customer.getBirthDate() != null)
      customerExists.setBirthDate(customerExists.getBirthDate());

    if (customer.getCpf() != null)
      customerExists.setCpf(customer.getCpf());

    if (customer.getCep() != null)
      customerExists.setCep(customer.getCep());

    return new ResponseEntity<>(customerRepository.save(customerExists), HttpStatus.OK);
  }

  public ResponseEntity<?> delete(int id) {
    Customer customer = customerRepository.findById(id);

    if (customer == null)
      return new ResponseEntity<>("Cliente não encontrado", HttpStatus.NOT_FOUND);

    customerRepository.delete(customer);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  public Customer searchCustomerByCpf(String cpf) {
    return customerRepository.findByCpf(cpf);
  }
}
