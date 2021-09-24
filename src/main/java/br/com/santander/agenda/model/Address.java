package br.com.santander.agenda.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String street;
  private String number;
  private String city;
  private String type;

  @ManyToOne
  private Contact contact;

  public Address(String street, String number, String city, String type) {
    this.street = street;
    this.number = number;
    this.city = city;
    this.type = type;
  }

  protected Address() {}

  public Integer getId() {
    return id;
  }

  public String getStreet() {
    return street;
  }

  public String getNumber() {
    return number;
  }

  public String getCity() {
    return city;
  }

  public String getType() {
    return type;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }
}
