package br.com.santander.agenda.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO implements Serializable {
  private String status;
  private String message;
  private Object data;

  public ResponseDTO(String status, String message) {
    this.status = status;
    this.message = message;
  }
}
