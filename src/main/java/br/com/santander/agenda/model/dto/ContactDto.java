package br.com.santander.agenda.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ContactDto implements Serializable {
  private String name;
  private String surname;
  private LocalDateTime birthdDate;
  private String nickname;
}
