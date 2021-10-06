package br.com.santander.agenda.controller;

import br.com.santander.agenda.model.dto.ResponseDTO;
import br.com.santander.agenda.service.AddressService;
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
@RequestMapping("/address")
@Api(
  tags = "Endereços",
  description = "Controller responsável pela listagem de Endereços"
)
@SwaggerDefinition(produces = "Application/Json")
public class AddressController {
  private AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @GetMapping
  @ApiOperation(
    value = "Listar todos os endereços",
    notes = "Método responsavel por listar todos os endereços",
    response = ResponseEntity.class,
    produces = "Application/Json"
  )
  public ResponseEntity<ResponseDTO> getAllAddresses() {
    try {
      URI uri = UriComponentsBuilder
        .fromPath("address")
        .buildAndExpand()
        .toUri();

      return ResponseEntity
        .created(uri)
        .body(
          new ResponseDTO(
            HttpStatus.OK.toString(),
            addressService.getAll().size() > 0
              ? "Confira a lista de endereços"
              : "Nenhum endereço registrado",
            addressService.getAll()
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
