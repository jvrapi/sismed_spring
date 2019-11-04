package br.com.sismed.domain;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_dados_bancarios")
public class DadosBancarios extends AbstractEntity<Long>{

	private Long id;
	private String banco;
	private String agencia;
	private String conta;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
