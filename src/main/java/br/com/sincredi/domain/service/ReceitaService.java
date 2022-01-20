package br.com.sincredi.domain.service;

import java.io.IOException;
import java.util.List;

import br.com.sincredi.api.dto.ReceitaDTO;
import br.com.sincredi.api.dto.ReceitaDTO.Request.Atualizacao;
import br.com.sincredi.api.exception.CustomException;

public interface ReceitaService {

	public List<Atualizacao> atualizarConta(List<ReceitaDTO.Request.Atualizacao> contas, String csvPath) throws RuntimeException, InterruptedException, CustomException, IOException;
	
}
