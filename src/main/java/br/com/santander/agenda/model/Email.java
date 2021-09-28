package br.com.santander.agenda.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "emails")
@AllArgsConstructor
@NoArgsConstructor
public class Email implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String email;

  @ManyToOne
  private Contact contact;

  public Integer getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }
}
