
package br.com.projeto.api.dtos;

import java.util.List;

public class CustomerSaleProductsDto {

  private CustomerDto customer;

  private List<ProductSalesDto> sales;

  public CustomerSaleProductsDto(CustomerDto customer, List<ProductSalesDto> productSalesList) {
    this.customer = customer;
    this.sales = productSalesList;
  }

  public CustomerDto getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerDto customer) {
    this.customer = customer;
  }

  public List<ProductSalesDto> getSales() {
    return sales;
  }

  public void setSales(List<ProductSalesDto> sales) {
    this.sales = sales;
  }
}