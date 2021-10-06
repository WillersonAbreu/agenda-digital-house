package br.com.santander.agenda.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.santander.agenda.AgendaApplication;
import br.com.santander.agenda.model.Address;
import br.com.santander.agenda.model.dto.FormLoginDto;
import br.com.santander.agenda.model.dto.JwtResponseDTO;
import br.com.santander.agenda.model.dto.ResponseDTO;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(
  classes = AgendaApplication.class,
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestInstance(Lifecycle.PER_CLASS)
public class AddressControllerTests {
  private final String AUTH_ENDPOINT = "/auth/login";
  private final String ADDRESSES_ENDPOINT = "/address";

  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int port;

  @Autowired
  public AddressControllerTests(TestRestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  private String getUrl() {
    return "http://localhost:" + port;
  }

  @BeforeAll
  public void init() {
    FormLoginDto loginForm = new FormLoginDto();
    loginForm.setName("administrador");
    loginForm.setPassword("boraSalvarContato");

    ResponseEntity<JwtResponseDTO> authResponse = restTemplate.postForEntity(
      this.getUrl() + AUTH_ENDPOINT,
      loginForm,
      JwtResponseDTO.class
    );

    assertNotNull(authResponse);

    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder(
      rt ->
        rt
          .getInterceptors()
          .add(
            (request, body, execution) -> {
              request
                .getHeaders()
                .add(
                  "Authorization",
                  "Bearer " + authResponse.getBody().getToken()
                );
              return execution.execute(request, body);
            }
          )
    );

    this.restTemplate = new TestRestTemplate(restTemplateBuilder);
  }

  @Test
  @Order(1)
  public void shouldReturnAnAddressesList() {
    ResponseEntity<ResponseDTO> response =
      this.restTemplate.exchange(
          this.getUrl() + ADDRESSES_ENDPOINT,
          HttpMethod.GET,
          null,
          ResponseDTO.class
        );

    List<Address> addressList = (List<Address>) response.getBody().getData();

    assertThat(addressList.size(), greaterThan(0));
  }
}
