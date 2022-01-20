package br.com.sincredi.api.exception;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.sincredi.entity.Result;

@RestControllerAdvice
public class ApiExceptionHandler{

	protected static final String MSG_USUARIO_ERRO = "Não foi possível realizar a operação";
	
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Result> handleConstraintViolation(ConstraintViolationException cve) {
		String msgTecnica = cve.getConstraintViolations().stream()
				.map(cv -> new StringBuilder(cv.getPropertyPath().toString().split("[.]")[1]).append(": ").append(cv.getMessage()))
				.findFirst().get().toString();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result(HttpStatus.BAD_REQUEST.value(), msgTecnica, MSG_USUARIO_ERRO));
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<Result> handleHttpMessageNotReadable(HttpMessageNotReadableException hmnre, JsonMappingException jme) throws Throwable {
		String msgTecnica = new StringBuilder(jme.getPath().get(1).getFieldName()).append(": valor inválido").toString();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result(HttpStatus.BAD_REQUEST.value(), msgTecnica, MSG_USUARIO_ERRO));
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Result> handle(Exception e) {
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new Result(INTERNAL_SERVER_ERROR.value(), e.toString(), MSG_USUARIO_ERRO));
	}
	
	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<Result> handleCustom(CustomException ce) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result(HttpStatus.BAD_REQUEST.value(), ce.getMessage(), MSG_USUARIO_ERRO));
	}


}