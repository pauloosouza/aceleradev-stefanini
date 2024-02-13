package br.com.projeto.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotEmpty(message = "Campo code é obrigatório")
  @Column(unique = true)
  private String code;

  @NotEmpty(message = "Campo name é obrigatório")
  private String name;

  @NotNull(message = "Campo price é obrigatório")
  private BigDecimal price;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<SaleProduct> saleProducts = new HashSet<>();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Set<SaleProduct> getSaleProducts() {
    return saleProducts;
  }

  public void setSaleProducts(Set<SaleProduct> saleProducts) {
    this.saleProducts = saleProducts;
  }

  // Método utilitário para adicionar uma relação de venda
  public void addSaleProduct(SaleProduct saleProduct) {
    saleProducts.add(saleProduct);
    saleProduct.setProduct(this);
  }

  // Método utilitário para remover uma relação de venda
  public void removeSaleProduct(SaleProduct saleProduct) {
    saleProducts.remove(saleProduct);
    saleProduct.setProduct(null);
  }
}
