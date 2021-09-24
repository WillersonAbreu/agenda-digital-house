package br.com.santander.agenda.service;

import br.com.santander.agenda.model.Contact;
import java.util.List;

public interface ContactService {
  Contact getContact(Integer id);
  List<Contact> getAll();
  Contact store(Contact contato);
  Contact update(Contact contact, Integer id);
}
