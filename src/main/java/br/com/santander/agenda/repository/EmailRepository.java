package br.com.santander.agenda.repository;

import br.com.santander.agenda.model.Email;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmailRepository extends JpaRepository<Email, Integer> {
  @Query(
    value = "SELECT e FROM Email e WHERE e.email = ?1",
    nativeQuery = false
  )
  List<Email> findByEmail(String email);
}
