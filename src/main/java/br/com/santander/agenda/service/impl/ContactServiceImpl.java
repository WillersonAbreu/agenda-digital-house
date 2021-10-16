package br.com.santander.agenda.service.impl;

import br.com.santander.agenda.model.Contact;
import br.com.santander.agenda.model.Email;
import br.com.santander.agenda.repository.ContactRepository;
import br.com.santander.agenda.repository.EmailRepository;
import br.com.santander.agenda.service.ContactService;
import br.com.santander.agenda.service.MailMarketService;
import br.com.santander.agenda.util.Base64Utils;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ContactServiceImpl implements ContactService {
  private EmailRepository emailRepository;
  private ContactRepository contactRepository;
  private MailMarketService mailMarketService;

  private Base64Utils base64Utils = new Base64Utils();

  @Autowired
  public ContactServiceImpl(
    EmailRepository emailRepository,
    ContactRepository contactRepository,
    MailMarketService mailMarketService
  ) {
    this.emailRepository = emailRepository;
    this.contactRepository = contactRepository;
    this.mailMarketService = mailMarketService;
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
    String contactImage = null;

    if (
      contact.getContactImage().getProfileImagePath().indexOf("data:image") !=
      -1
    ) {
      contactImage =
        this.base64Utils.getBase64(
            contact.getContactImage().getProfileImagePath()
          );
      contact.getContactImage().setProfileImagePath(contactImage);
    }

    for (Email email : emails) {
      String emailToBeSaved = email.getEmail();
      List<Email> hasEmail = this.emailRepository.findByEmail(emailToBeSaved);

      if (!hasEmail.isEmpty() && hasEmail.size() > 0) {
        contactExists = true;
      }
    }

    if (!contactExists) {
      Contact response = contactRepository.save(contact);

      if (response != null) {
        response
          .getEmails()
          .stream()
          .forEach(
            m -> {
              try {
                SendResult res =
                  this.mailMarketService.sendMailToTopic(m.getEmail());
                if (res != null) {
                  log.info(
                    "A mensagem: {} foi enviada para o tópico: {}",
                    res.getProducerRecord().value(),
                    res.getProducerRecord().topic()
                  );
                }
              } catch (InterruptedException e) {
                e.printStackTrace();
              } catch (ExecutionException e) {
                e.printStackTrace();
              } catch (TimeoutException e) {
                e.printStackTrace();
              }
            }
          );
      }
      return response;
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
