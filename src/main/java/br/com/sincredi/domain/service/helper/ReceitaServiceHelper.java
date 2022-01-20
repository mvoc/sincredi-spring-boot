package br.com.sincredi.domain.service.helper;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.sincredi.api.dto.ReceitaDTO;
import br.com.sincredi.api.dto.ReceitaDTO.Request.Atualizacao;
import br.com.sincredi.api.dto.ReceitaDTO.Request.ReceitaCSV;

/**
 * @author gabriel_stabel<gabriel_stabel@sicredi.com.br>
 */
@Component
public class ReceitaServiceHelper {
	// Esta é a implementação interna do "servico" do banco central. Veja o código fonte abaixo para ver os formatos esperados pelo Banco Central neste cenário.
	
    public boolean atualizarConta(String agencia, String conta, double saldo, String status)
            throws RuntimeException, InterruptedException {
		
			
        // Formato agencia: 0000
        if (agencia == null || agencia.length() != 4) {
            return false;
        }
        
        // Formato conta: 000000
        if (conta == null || conta.length() != 6) {
            return false;
        }
        
        // Tipos de status validos:
        List<String> tipos = new ArrayList<>();
        tipos.add("A");
        tipos.add("I");
        tipos.add("B");
        tipos.add("P");                
                
        if (status == null || !tipos.contains(status)) {
            return false;
        }
        
        // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
        long wait = Math.round(Math.random() * 4000) + 1000;
        Thread.sleep(wait);

        // Simula cenario de erro no serviço (0,1% de erro)
        long randomError = Math.round(Math.random() * 1000);
        if (randomError == 500) {
            throw new RuntimeException("Error");
        }

        return true;
    }
    
    public List<ReceitaDTO.Request.Atualizacao> csvToReceitaAtualizacao(String csvPath) throws IOException {
    	Reader reader = Files.newBufferedReader(Paths.get(csvPath));

        CsvToBean<ReceitaDTO.Request.ReceitaCSV> csvToBean = new CsvToBeanBuilder<ReceitaDTO.Request.ReceitaCSV>(reader)
                .withType(ReceitaDTO.Request.ReceitaCSV.class)
                .withSeparator(';')
                .build();

        List<ReceitaDTO.Request.ReceitaCSV> receitas = csvToBean.parse();
        List<ReceitaDTO.Request.Atualizacao> attList = new ArrayList<>();

        for (ReceitaDTO.Request.ReceitaCSV receita : receitas) {
        	System.out.println(receita);
        	attList.add(this.ReceitaCsvToAtualizacao(receita));
        }
        return attList;
    }

	private ReceitaDTO.Request.Atualizacao ReceitaCsvToAtualizacao(ReceitaCSV receitaCSV) {
		ReceitaDTO.Request.Atualizacao att = new Atualizacao(); 
		att.setAgencia(receitaCSV.getAgencia());
		att.setConta(receitaCSV.getConta());
		att.setStatus(receitaCSV.getStatus());
		att.setSaldo(new BigDecimal(receitaCSV.getSaldo()));
		return att;
	}

}
