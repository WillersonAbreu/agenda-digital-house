package br.com.santander.agenda.service;

import br.com.santander.agenda.model.Contact;
import br.com.santander.agenda.repository.ContactRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
  ContactRepository contactRepository;

  public ContactServiceImpl(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  @Override
  public Contact getContact(Integer id) {
    Contact contact = contactRepository.getById(id);
    return contact;
  }

  @Override
  public List<Contact> getAll() {
    return contactRepository.findAll();
  }

  @Override
  public Contact store(Contact contact) {
    if (contact != null) {
      return contactRepository.save(contact);
    } else {
      return null;
    }
  }

  @Override
  public Contact update(Contact contact, Integer id) {
    contact.setId(id);
    return contactRepository.save(contact);
  }
}
