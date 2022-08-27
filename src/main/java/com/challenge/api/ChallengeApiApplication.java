package com.challenge.api;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import static org.springdoc.core.Constants.ALL_PATTERN;

@SpringBootApplication
public class ChallengeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApiApplication.class, args);
	}

	@Bean
	@Profile("!prod")
	public GroupedOpenApi actuatorApi(//OpenApiCustomiser actuatorOpenApiCustomiser,
									  //OperationCustomizer actuatorCustomizer,
									  WebEndpointProperties endpointProperties,
									  @Value("${springdoc.version}") String appVersion) {
		return GroupedOpenApi.builder()
				.group("Actuator")
				.pathsToMatch(endpointProperties.getBasePath()+ ALL_PATTERN)
				//.addOpenApiCustomiser(actuatorOpenApiCustomiser)
				.addOpenApiCustomiser(openApi -> openApi.info(new Info().title("Actuator API").version(appVersion)))
				//.addOperationCustomizer(actuatorCustomizer)
				.pathsToExclude("/health/*")
				.build();
	}

	@Bean
	public GroupedOpenApi usersGroup(@Value("${springdoc.version}") String appVersion) {
		return GroupedOpenApi.builder().group("API")
				.addOperationCustomizer((operation, handlerMethod) -> {
					operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
					return operation;
				})
				.addOpenApiCustomiser(openApi -> openApi.info(new Info().title("API").version(appVersion)))
				.packagesToScan("com.challenge.api.controller")
				.build();
	}
}
