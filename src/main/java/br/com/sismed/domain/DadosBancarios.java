package br.com.sismed.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_dados_bancarios")
public class DadosBancarios extends AbstractEntity{

	
	@Column(name = "banco", nullable = false)
	private String banco;
	
	
	@Column(name = "agencia", nullable = false)
	private String agencia;
	
	
	@Column(name = "conta", nullable = false)
	private String conta;
	
	
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	
	
}
