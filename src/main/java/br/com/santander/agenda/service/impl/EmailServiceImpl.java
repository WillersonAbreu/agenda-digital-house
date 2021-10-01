package br.com.santander.agenda.service.impl;

import br.com.santander.agenda.model.Email;
import br.com.santander.agenda.repository.EmailRepository;
import br.com.santander.agenda.service.EmailService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
  private EmailRepository emailRepository;

  public EmailServiceImpl(EmailRepository emailRepository) {
    this.emailRepository = emailRepository;
  }

  @Override
  public List<Email> getAllEmails() {
    return this.emailRepository.findAll();
  }
}
