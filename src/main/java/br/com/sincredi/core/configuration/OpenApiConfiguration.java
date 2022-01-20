package br.com.sincredi.core.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfiguration {
	
	@Value("${project.version}")
	private String version;
	
	@Bean
	public GroupedOpenApi groupApisReceita() {
		return GroupedOpenApi.builder().group("receita")
				.pathsToMatch("/receita/**")
				.addOpenApiCustomiser(openApi -> openApi.setInfo(new Info().title("Receita").version(version))).build();
	}
	
}