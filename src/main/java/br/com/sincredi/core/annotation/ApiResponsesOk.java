package br.com.sincredi.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.http.MediaType;

import br.com.sincredi.entity.Result;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
		 @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", 
				 content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Result.class)) }),
		 @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", 
				 content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Result.class)) }),
		 @ApiResponse(responseCode = "404", description = "Não encontrado", 
				 content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Result.class)) }),
		 @ApiResponse(responseCode = "405", description = "Método não suportado", 
				 content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Result.class)) }),
		 @ApiResponse(responseCode = "500", description = "Erro genérico. A mensagem varia de acordo com a operação", 
		 		 content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Result.class)) })
})
public @interface ApiResponsesOk {}
