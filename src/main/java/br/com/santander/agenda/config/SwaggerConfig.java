package br.com.santander.agenda.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket swagger() {
    return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(apiInfo())
      .select()
      .apis(RequestHandlerSelectors.basePackage("br.com.santander.agenda"))
      .paths(PathSelectors.ant("/**"))
      .build();
    //		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(
    //				RequestHandlerSelectors.any())
    //				.paths(PathSelectors.ant("/**"))
    //				.build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
      "Agenda de Contato",
      "1.0",
      null,
      null,
      null,
      null,
      null,
      Collections.emptyList()
    );
  }
}
