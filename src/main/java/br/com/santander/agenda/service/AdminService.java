package br.com.santander.agenda.service;

import br.com.santander.agenda.model.Admin;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminService {
  public Optional<Admin> findByName(String name);

  public UserDetails loadUserByUsername(String name);
}
