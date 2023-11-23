package com.codesmachine.springbootrestapi;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring boot Blog APIs",
				description = "Spring boot Blog APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Gasana Theophile",
						email = "gasana141414@gmail.com",
						url = "hhttps://github.com/Gasana14"
				),
				license = @License(
						name = "Apache 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring boot Blog External Documentation",
				url = "https://github.com/Gasana14"
		)
)
public class SpringbootRestApiApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestApiApplication.class, args);
		System.out.println("App Running");
	}

}
