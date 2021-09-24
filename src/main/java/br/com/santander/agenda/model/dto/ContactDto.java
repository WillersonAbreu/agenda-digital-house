package br.com.santander.agenda.model.dto;

import br.com.santander.agenda.model.Contact;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ContactDto implements Serializable {
  private String name;
  private String surname;
  private LocalDateTime birthdDate;
  private String nickname;

  public ContactDto(
    String name,
    String surname,
    LocalDateTime birthdDate,
    String nickname
  ) {
    this.name = name;
    this.surname = surname;
    this.birthdDate = birthdDate;
    this.nickname = nickname;
  }

  public String getNome() {
    return name;
  }

  public String getSobrenome() {
    return surname;
  }

  public LocalDateTime getDataNascimento() {
    return birthdDate;
  }

  public String getApelido() {
    return nickname;
  }

  public Contact converte() {
    return new Contact(name, surname, birthdDate, nickname);
  }
}
