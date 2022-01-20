package br.com.sincredi.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.sincredi.core.annotation.ValoresPermitidos;
import lombok.Data;
import lombok.EqualsAndHashCode;

public enum ReceitaDTO {;

	private interface Agencia {
		@NotBlank @Size(min = 4, max = 4)
		String getAgencia();
	}
	
	private interface Conta {
		@NotBlank @Size(min = 6, max = 6)
		String getConta();
	}
	
	private interface Saldo {
		@NotNull @Digits(integer = 13, fraction = 2)
		BigDecimal getSaldo();
	}
	
	private interface Status {
		@NotBlank @ValoresPermitidos({"A","I","B","P"})
		String getStatus();
	}
	
	public enum Request {;
		
		@Data
        private static class Base implements Agencia, Conta, Status {
    		private String agencia;
    		private String conta;
    		private String status;
    		
    		public void setConta(String conta) {
    			this.conta = conta.replaceAll("\\D", "");
    		}
        }
		
		@Data
		@EqualsAndHashCode(callSuper = true)
        public static class ReceitaCSV extends Base {
    		private String saldo;
    		
    		public void setSaldo(String saldo) {
    			this.saldo = saldo.replaceAll(",", ".");
    		}
        }
		
    	@Data
    	@EqualsAndHashCode(callSuper = true)
    	@JsonIgnoreProperties(value = {"resultado"}, allowGetters = true)
        public static class Atualizacao extends Base implements Saldo {
    		private BigDecimal saldo;
    		private boolean resultado;
        }
    	
    }
	
}
