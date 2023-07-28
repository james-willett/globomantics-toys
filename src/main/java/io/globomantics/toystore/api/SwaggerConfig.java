package io.globomantics.toystore.api;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public static final String SECURITY_SCHEME = "bearerAuth";

    @Bean
    public OpenAPI api() {
        Contact contact = new Contact();
        contact.setName("Pluralsight");
        contact.setUrl("https://pluralsight.com");

        return new OpenAPI()
          .info(new Info()
            .title("Globomantics Toys API")
            .description("This is an API for a fictional company called Globomantics Toys")
            .contact(contact)
            .version("1.0.0")
          )
          .components(new Components().addSecuritySchemes(SECURITY_SCHEME, new SecurityScheme()
            .name(SECURITY_SCHEME)
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
          ));
    }
}
