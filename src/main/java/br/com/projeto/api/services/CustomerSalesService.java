package br.com.projeto.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.dtos.CustomerDto;
import br.com.projeto.api.dtos.CustomerSaleProductsDto;
import br.com.projeto.api.dtos.ProductSalesDto;
import br.com.projeto.api.dtos.ViaCepDto;
import br.com.projeto.api.models.Customer;
import br.com.projeto.api.models.Sale;
import br.com.projeto.api.repositories.CustomerRepository;
import br.com.projeto.api.repositories.CustomerSalesRepository;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CustomerSalesService {

  @Autowired
  private CustomerSalesRepository customerSalesRepository;

  @Autowired
  private CustomerRepository customerRepository;

  private final WebClient webClient;

  public CustomerSalesService(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("https://viacep.com.br").build();
  }

  public ResponseEntity<?> listSalesToCustomer(String cpf) {
    Customer customer = this.customerRepository.findByCpf(cpf);

    if (customer == null) {
      return new ResponseEntity<>("Cliente não encontrado", HttpStatus.NOT_FOUND);
    }

    ViaCepDto cepResponse = this.webClient.get()
        .uri("/ws/{cep}/json/", customer.getCep())
        .retrieve()
        .bodyToMono(ViaCepDto.class)
        .block();

    List<Sale> customerSales = this.customerSalesRepository.findByCustomer(customer);

    List<ProductSalesDto> productSalesList = customerSales.stream()
        .flatMap(sale -> sale.getSaleProducts().stream())
        .map(saleProduct -> new ProductSalesDto(
            saleProduct.getProduct().getId(),
            saleProduct.getProduct().getCode(),
            saleProduct.getProduct().getName(),
            saleProduct.getProduct().getPrice(),
            saleProduct.getQuantity()))
        .collect(Collectors.toList());

    CustomerDto customerDto = new CustomerDto(
        customer.getId(),
        customer.getName(),
        customer.getBirthDate(),
        customer.getCpf(),
        cepResponse.getLogradouro() + ", " + cepResponse.getBairro() + ", " + cepResponse.getLocalidade() + " - "
            + cepResponse.getUf(),
        customer.getCep());

    CustomerSaleProductsDto customerSaleProductsDto = new CustomerSaleProductsDto(customerDto, productSalesList);

    return new ResponseEntity<>(customerSaleProductsDto, HttpStatus.OK);
  }
}
