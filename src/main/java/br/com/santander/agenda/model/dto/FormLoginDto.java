package br.com.santander.agenda.model.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormLoginDto implements Serializable {
  private String name;
  private String password;
}
