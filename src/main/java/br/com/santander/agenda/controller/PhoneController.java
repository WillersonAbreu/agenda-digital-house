package br.com.santander.agenda.controller;

import br.com.santander.agenda.model.dto.ResponseDTO;
import br.com.santander.agenda.service.PhoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/phone")
@Api(
  tags = "Telefones",
  description = "Controller responsável pela listagem de Telefones"
)
@SwaggerDefinition(produces = "Application/Json")
public class PhoneController {
  private PhoneService phoneService;

  public PhoneController(PhoneService phoneService) {
    this.phoneService = phoneService;
  }

  @GetMapping
  @ApiOperation(
    value = "Listar todos os telefones",
    notes = "Método responsavel por listar todos os telefones",
    response = ResponseEntity.class,
    produces = "Application/Json"
  )
  public ResponseEntity<ResponseDTO> getAllPhones() {
    try {
      URI uri = UriComponentsBuilder.fromPath("phone").buildAndExpand().toUri();

      return ResponseEntity
        .created(uri)
        .body(
          new ResponseDTO(
            HttpStatus.OK.toString(),
            phoneService.getAllPhones().size() > 0
              ? "Confira a lista de telefones"
              : "Nenhum telefone registrado",
            phoneService.getAllPhones()
          )
        );
    } catch (Exception e) {
      return new ResponseEntity<ResponseDTO>(
        new ResponseDTO(HttpStatus.BAD_REQUEST.toString(), e.getMessage()),
        HttpStatus.BAD_REQUEST
      );
    }
  }
}
