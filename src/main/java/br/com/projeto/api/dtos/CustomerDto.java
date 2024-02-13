package br.com.projeto.api.dtos;

import java.util.Date;

public class CustomerDto {
  private int id;

  private String name;

  private Date birthDate;

  private String cpf;

  private String address;

  private String cep;

  public CustomerDto(int id, String name, Date birthDate, String cpf, String address, String cep) {
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
    this.cpf = cpf;
    this.address = address;
    this.cep = cep;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }
}