package br.com.projeto.api.dtos;

import java.time.LocalDate;

public class SaleProductDto {

  private int id;

  private String code;

  private String name;

  private int quantity;

  private Double price;

  private LocalDate date;

  public SaleProductDto(int id, String code, String name, int quantity, Double price, LocalDate date) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.quantity = quantity;
    this.price = price;
    this.date = date;
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

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

}