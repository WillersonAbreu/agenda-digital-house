package br.com.santander.agenda.model.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDTO implements Serializable {
  private String token;
}
