package br.com.santander.agenda.controller;

import br.com.santander.agenda.model.dto.ResponseDTO;
import br.com.santander.agenda.service.EmailService;
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
@RequestMapping("/email")
@Api(
  tags = "Emails",
  description = "Controller responsável pela listagem de emails"
)
@SwaggerDefinition(produces = "Application/Json")
public class EmailController {
  private EmailService emailService;

  public EmailController(EmailService emailService) {
    this.emailService = emailService;
  }

  @GetMapping
  @ApiOperation(
    value = "Listar todos os emails",
    notes = "Método responsavel por listar todos os emails",
    response = ResponseEntity.class,
    produces = "Application/Json"
  )
  public ResponseEntity<ResponseDTO> getAllPhones() {
    try {
      URI uri = UriComponentsBuilder.fromPath("email").buildAndExpand().toUri();

      return ResponseEntity
        .created(uri)
        .body(
          new ResponseDTO(
            HttpStatus.OK.toString(),
            emailService.getAllEmails().size() > 0
              ? "Confira a lista de emails"
              : "Nenhum email registrado",
            emailService.getAllEmails()
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
