package br.com.projeto.api.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sales")
public class Sale {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private Customer customer;

  @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<SaleProduct> saleProducts = new HashSet<>();

  private LocalDate date;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Set<SaleProduct> getSaleProducts() {
    return saleProducts;
  }

  public void setSaleProducts(Set<SaleProduct> saleProducts) {
    this.saleProducts = saleProducts;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public void addProduct(Product product, int quantity) {
    SaleProduct saleProduct = new SaleProduct(this, product, quantity);
    saleProducts.add(saleProduct);
    product.getSaleProducts().add(saleProduct);
  }
}
