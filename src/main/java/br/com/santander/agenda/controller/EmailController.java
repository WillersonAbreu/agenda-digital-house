package br.com.santander.agenda.controller;

import br.com.santander.agenda.model.Email;
import br.com.santander.agenda.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<List<Email>> getAllPhones() {
    return ResponseEntity.ok(emailService.getAllEmails());
  }
}
