package br.com.santander.agenda.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phones")
public class Phone implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String residential;
  private String cell;
  private String message;

  @ManyToOne
  private Contact contact;

  public Phone(String residential, String cell, String message) {
    this.residential = residential;
    this.cell = cell;
    this.message = message;
  }

  protected Phone() {}

  public String getResidential() {
    return residential;
  }

  public String getCell() {
    return cell;
  }

  public String getMessage() {
    return message;
  }
}
