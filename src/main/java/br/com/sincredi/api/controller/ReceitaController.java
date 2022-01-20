package br.com.sincredi.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sincredi.api.dto.ReceitaDTO.Request.Atualizacao;
import br.com.sincredi.api.exception.CustomException;
import br.com.sincredi.core.annotation.ApiResponsesOk;
import br.com.sincredi.domain.service.ReceitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@RestController
@RequestMapping("receita")
@Tag(name = "receita", description = "APIs de Receita")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;
	
	@PutMapping
	@Operation(summary = "Atualizar Conta", description = "Atualiza uma ou mais contas a partir de um arquivo CSV separado por vígulas ou por uma lista em JSON informada no body da requisição.")
	@ApiResponsesOk
	public List<Atualizacao> atualizarConta( @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "<b>Contrato de entrada:</b><br><br>"
			+ "<div>Este método recebe uma lista de objetos JSON com o padrão [{obj},{obj}]. Cada objeto deve conter os seguintes atributos: <br>"
			+ "<ul><li> agencia: Código da agência.</li>"
			+ "<li> conta: Número da conta.</li>"
			+ "<li> saldo: Saldo para atualizar.</li>"
			+ "<li> status: Código do status. Valores permitidos: [A,I,B,P].</li></ul>"
			+ "<br></div>"
			)
			@RequestBody(required = false) @Size(min = 1) List<@Valid Atualizacao> contas, 
			@Parameter(description = "Caminho do arquivo CSV separado por vígulas no seguinte padrão: C:/Pasta1/Pasta2/Pasta3/arquivoCsv.csv", example = "C:/Users/Usuario/Desktop/teste.csv") @RequestParam(required = false) String csvPath) throws RuntimeException, InterruptedException, CustomException, IOException {
		return receitaService.atualizarConta(contas, csvPath);
	}

}