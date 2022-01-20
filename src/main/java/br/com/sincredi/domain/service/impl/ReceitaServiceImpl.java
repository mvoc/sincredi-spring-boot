package br.com.sincredi.domain.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sincredi.api.dto.ReceitaDTO.Request.Atualizacao;
import br.com.sincredi.api.exception.CustomException;
import br.com.sincredi.domain.service.ReceitaService;
import br.com.sincredi.domain.service.helper.ReceitaServiceHelper;

/**
 * @author gabriel_stabel<gabriel_stabel@sicredi.com.br>
 */
@Service
public class ReceitaServiceImpl implements ReceitaService{
	// Esta é a implementação interna do "servico" do banco central. Veja o código fonte abaixo para ver os formatos esperados pelo Banco Central neste cenário.
	
	@Autowired
	private ReceitaServiceHelper receitaServiceHelper;

	@Override
	public List<Atualizacao> atualizarConta(List<Atualizacao> contas, String csvPath) throws RuntimeException, InterruptedException, CustomException, IOException {
		
		if((contas == null && csvPath == null) || (contas != null && csvPath != null)) {
			throw new CustomException("Deve-se informar apenas um body ou apenas um caminho para o arquivo CSV com as receitas.");
		}
		
		if(contas == null) {
			contas = receitaServiceHelper.csvToReceitaAtualizacao(csvPath);
		} 		
		
		for(Atualizacao conta : contas) {
			conta.setResultado(receitaServiceHelper.atualizarConta(conta.getAgencia(), conta.getConta(), conta.getSaldo().doubleValue(), conta.getStatus()));
		}
		
		return contas;
	}
}
