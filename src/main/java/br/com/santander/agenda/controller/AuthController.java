package br.com.santander.agenda.controller;

import br.com.santander.agenda.config.JwtTokenUtil;
import br.com.santander.agenda.model.Admin;
import br.com.santander.agenda.model.dto.FormLoginDto;
import br.com.santander.agenda.model.dto.JwtResponseDTO;
import br.com.santander.agenda.service.AdminService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private AuthenticationManager authenticationManager;

  private JwtTokenUtil jwtTokenUtil;

  private AdminService adminService;

  @Autowired
  public AuthController(
    AuthenticationManager authenticationManager,
    JwtTokenUtil jwtTokenUtil,
    AdminService adminService
  ) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
    this.adminService = adminService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody FormLoginDto authForm)
    throws Exception {
    Optional<Admin> user = adminService.findByName(authForm.getName());

    this.authenticate(authForm.getName(), authForm.getPassword());
    final UserDetails userDetails = adminService.loadUserByUsername(
      authForm.getName()
    );

    final String token = jwtTokenUtil.generateToken(userDetails, user);
    JwtResponseDTO response = new JwtResponseDTO();
    response.setToken(token);
    return ResponseEntity.ok(response);
  }

  private void authenticate(String email, String senha) throws Exception {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(email, senha)
      );
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("Senha está incorreta", e);
    }
  }
}
