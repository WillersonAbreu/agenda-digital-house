package br.com.santander.agenda.model.dto;

import java.io.Serializable;

public class TokenDto implements Serializable {
  private String token;
  private String type;

  public TokenDto(String token, String type) {
    this.token = token;
    this.type = type;
  }

  public String getToken() {
    return token;
  }

  public String getTipo() {
    return type;
  }
}
