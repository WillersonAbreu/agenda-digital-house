package br.com.santander.agenda.controller;

import br.com.santander.agenda.model.Contact;
import br.com.santander.agenda.model.dto.ResponseDTO;
import br.com.santander.agenda.service.ContactService;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/contact")
public class ContactController {
  ContactService contactService;

  public ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  @GetMapping
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

  @PutMapping(value = "contact/{id}")
  public ResponseEntity<Contact> update(
    @PathVariable Integer id,
    @RequestBody Contact contact
  ) {
    Contact response = contactService.update(contact, id);

    URI uri = UriComponentsBuilder
      .fromPath("contact/{id}")
      .buildAndExpand(contact.getId())
      .toUri();
    return ResponseEntity.created(uri).body(response);
  }

  @DeleteMapping(value = "contact/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    contactService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
