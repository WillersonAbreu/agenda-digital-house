package br.com.santander.agenda.repository;

import br.com.santander.agenda.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {}
