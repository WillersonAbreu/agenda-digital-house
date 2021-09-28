package br.com.santander.agenda.service;

import br.com.santander.agenda.model.Contact;
import br.com.santander.agenda.model.Email;
import br.com.santander.agenda.repository.ContactRepository;
import br.com.santander.agenda.repository.EmailRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
  EmailRepository emailRepository;
  ContactRepository contactRepository;

  @Autowired
  public ContactServiceImpl(
    EmailRepository emailRepository,
    ContactRepository contactRepository
  ) {
    this.emailRepository = emailRepository;
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
  public Contact store(Contact contact) throws Exception {
    List<Email> emails = contact.getEmails();
    Boolean contactExists = false;

    for (Email email : emails) {
      String emailToBeSaved = email.getEmail();
      List<Email> hasEmail = this.emailRepository.findByEmail(emailToBeSaved);

      if (!hasEmail.isEmpty() && hasEmail.size() > 0) {
        contactExists = true;
      }
    }

    if (!contactExists) {
      return contactRepository.save(contact);
    } else {
      throw new Exception("Já existe usuário cadastrado com o mesmo e-mail");
    }
  }

  @Override
  public Contact update(Contact contact, Integer id) {
    contact.setId(id);
    return contactRepository.save(contact);
  }

  @Override
  public void delete(Integer id) {
    contactRepository.deleteById(id);
  }
}
