package br.com.santander.agenda.controller;

import br.com.santander.agenda.model.Address;
import br.com.santander.agenda.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<List<Address>> getAllAddresses() {
    return ResponseEntity.ok(addressService.getAll());
  }
}
