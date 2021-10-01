package br.com.santander.agenda.controller;

import br.com.santander.agenda.model.Phone;
import br.com.santander.agenda.service.PhoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<List<Phone>> getAllPhones() {
    return ResponseEntity.ok(phoneService.getAllPhones());
  }
}
