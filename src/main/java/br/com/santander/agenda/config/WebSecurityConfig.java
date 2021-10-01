package br.com.santander.agenda.config;

import br.com.santander.agenda.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private JwtUserDetailsService jwtUserDetailsService;

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Autowired
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(jwtUserDetailsService)
      .passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .csrf()
      .disable()
      .headers()
      .frameOptions()
      .sameOrigin()
      .and()
      // Don't check the following routes
      .authorizeRequests()
      .antMatchers(
        "/h2-console/**",
        "/auth/login",
        "/actuator/**",
        "classpath:/",
        "classpath:/images/"
      )
      .permitAll()
      // Check the following routes
      .anyRequest()
      .authenticated()
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    httpSecurity.addFilterBefore(
      jwtRequestFilter,
      UsernamePasswordAuthenticationFilter.class
    );
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web
      .ignoring()
      .antMatchers(HttpMethod.OPTIONS, "/**")
      .antMatchers(
        "/v2/api-docs",
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/webjars/**",
        "/images/**"
      );
  }
}
