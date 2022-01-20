package br.com.sincredi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

	private Integer statusCode;

	private String msgTecnica = new String();
	
	private String msgUsuario = new String();

}