package br.com.santander.agenda.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private String surname;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime birthdate;

  private String nickname;

  @OneToMany(
    mappedBy = "contact",
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER
  )
  @Fetch(FetchMode.SUBSELECT)
  private List<Phone> phones = new ArrayList<>();

  @OneToMany(
    mappedBy = "contact",
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER
  )
  @Fetch(FetchMode.SUBSELECT)
  private List<Address> addresses = new ArrayList<>();

  @OneToMany(
    mappedBy = "contact",
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER
  )
  @Fetch(FetchMode.SUBSELECT)
  private List<Email> emails = new ArrayList<>();

  @OneToOne(mappedBy = "contact")
  @JsonIgnoreProperties("contact")
  private ContactImage contactImage;

  public Contact(
    String name,
    String surname,
    LocalDateTime birthdate,
    String nickname
  ) {
    this.name = name;
    this.surname = surname;
    this.birthdate = birthdate;
    this.nickname = nickname;
  }

  protected Contact() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public LocalDateTime getBirthdate() {
    return birthdate;
  }

  public String getNickname() {
    return nickname;
  }

  public List<Phone> getPhones() {
    return phones;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public List<Email> getEmails() {
    return emails;
  }

  public ContactImage getContactImage() {
    return contactImage;
  }
}
