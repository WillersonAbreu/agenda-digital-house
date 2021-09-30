package br.com.santander.agenda.controller;

import br.com.santander.agenda.model.Contact;
import br.com.santander.agenda.model.dto.ResponseDTO;
import br.com.santander.agenda.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.AuthorizationScope;
import io.swagger.annotations.SwaggerDefinition;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/contact")
@Api(
  tags = "Contatos",
  description = "Controller responsável pelo CRUD de contatos"
)
@SwaggerDefinition(produces = "Application/Json")
public class ContactController {
  ContactService contactService;

  public ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  @GetMapping
  @ApiOperation(
    value = "Listar todos os contatos",
    notes = "Método responsavel por listar todos os contatos",
    response = ResponseEntity.class,
    produces = "Application/Json"
  )
  @AuthorizationScope(scope = "global", description = "Global")
  public ResponseEntity<List<Contact>> getAllContacts() {
    return ResponseEntity.ok(contactService.getAll());
  }

  @PostMapping
  public ResponseEntity<ResponseDTO> store(@RequestBody Contact contact) {
    Contact response;
    try {
      response = contactService.store(contact);

      URI uri = UriComponentsBuilder
        .fromPath("contact/{id}")
        .buildAndExpand(contact.getId())
        .toUri();

      return ResponseEntity
        .created(uri)
        .body(
          new ResponseDTO(
            HttpStatus.CREATED.toString(),
            "Contato salvo com sucesso",
            response
          )
        );
    } catch (Exception e) {
      return new ResponseEntity<ResponseDTO>(
        new ResponseDTO(HttpStatus.BAD_REQUEST.toString(), e.getMessage()),
        HttpStatus.BAD_REQUEST
      );
    }
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ResponseDTO> update(
    @PathVariable Integer id,
    @RequestBody Contact contact
  ) {
    try {
      Contact response = contactService.update(contact, id);

      URI uri = UriComponentsBuilder
        .fromPath("contact/{id}")
        .buildAndExpand(contact.getId())
        .toUri();

      return ResponseEntity
        .created(uri)
        .body(
          new ResponseDTO(
            HttpStatus.OK.toString(),
            "Contato atualizado com sucesso",
            response
          )
        );
    } catch (Exception e) {
      return new ResponseEntity<ResponseDTO>(
        new ResponseDTO(HttpStatus.BAD_REQUEST.toString(), e.getMessage()),
        HttpStatus.BAD_REQUEST
      );
    }
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<ResponseDTO> delete(@PathVariable Integer id) {
    try {
      contactService.delete(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return new ResponseEntity<ResponseDTO>(
        new ResponseDTO(HttpStatus.BAD_REQUEST.toString(), e.getMessage()),
        HttpStatus.BAD_REQUEST
      );
    }
  }
}
