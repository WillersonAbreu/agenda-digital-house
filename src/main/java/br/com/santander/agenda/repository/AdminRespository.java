package br.com.santander.agenda.repository;

import br.com.santander.agenda.model.Admin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRespository extends JpaRepository<Admin, Integer> {
  Optional<Admin> findByName(String name);
}
