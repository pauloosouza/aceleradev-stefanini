package br.com.projeto.api.dtos;

import java.math.BigDecimal;

public class ProductSalesDto {
  private int id;
  private String code;
  private String name;
  private BigDecimal price;
  private long totalSales;

  public ProductSalesDto(int id, String code, String name, BigDecimal price, long totalSales) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.price = price;
    this.totalSales = totalSales;
  }

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

  public void setTotalSales(long totalSales) {
    this.totalSales = totalSales;
  }

  public long getTotalSales() {
    return totalSales;
  }
}