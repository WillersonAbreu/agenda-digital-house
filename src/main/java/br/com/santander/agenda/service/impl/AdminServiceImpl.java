package br.com.santander.agenda.service.impl;

import br.com.santander.agenda.model.Admin;
import br.com.santander.agenda.repository.AdminRespository;
import br.com.santander.agenda.service.AdminService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
  private AdminRespository adminRespository;

  @Autowired
  public AdminServiceImpl(AdminRespository adminRespository) {
    this.adminRespository = adminRespository;
  }

  @Override
  public Optional<Admin> findByName(String name) {
    return adminRespository.findByName(name);
  }

  @Override
  public UserDetails loadUserByUsername(String name)
    throws UsernameNotFoundException {
    Optional<Admin> usuario = this.adminRespository.findByName(name);

    if (usuario.isPresent()) {
      return new User(
        usuario.get().getName(),
        usuario.get().getPassword(),
        new ArrayList<>()
      );
    } else {
      throw new UsernameNotFoundException(
        "Usuário não encontrado com o nome: " + name
      );
    }
  }
}
