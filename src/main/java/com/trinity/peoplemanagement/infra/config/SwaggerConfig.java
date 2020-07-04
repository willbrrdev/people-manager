package com.trinity.peoplemanagement.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Value("${swagger.owner.url}")
	private String ulr;
	@Value("${swagger.owner.name}")
	private String nameOwner;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.trinity.peoplemanagement.api.resources"))
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/api");
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	    		.title("Gerenciamento de Pessoas - API RESTful")
	    		.description("Api para gerenciamento de cadastro de pessoas.")
	    		.version("1.0.0")
	    		.contact(new Contact("Williams Gomes", "", "williamsgomess@outlook.com"))
	    		.build();
	}

}
