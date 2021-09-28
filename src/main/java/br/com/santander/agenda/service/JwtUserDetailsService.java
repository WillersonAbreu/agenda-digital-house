package br.com.santander.agenda.service;

import br.com.santander.agenda.model.Admin;
import br.com.santander.agenda.repository.AdminRespository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
  @Autowired
  private AdminRespository adminRepository;

  @Override
  public UserDetails loadUserByUsername(String name)
    throws UsernameNotFoundException {
    Optional<Admin> admin = adminRepository.findByName(name);

    if (admin.isPresent()) {
      return new User(
        admin.get().getName(),
        admin.get().getPassword(),
        new ArrayList<>()
      );
    } else {
      throw new UsernameNotFoundException(
        "Usuário não encontrado com o nome: " + name
      );
    }
  }
}
