package br.com.santander.agenda.repository;

import br.com.santander.agenda.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {}
