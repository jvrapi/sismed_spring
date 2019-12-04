package br.com.sismed.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_dados_bancarios")
public class DadosBancarios extends AbstractEntity{

	@NotBlank(message = "Banco é um campo obrigatório")
	@Column(name = "banco", nullable = false)
	private String banco;
	
	@NotBlank(message = "Agência é um campo obrigatório")
	@Size(min = 4, max = 4, message = "A agência deve conter {min} digitos. ")
	@Column(name = "agencia", nullable = false)
	private String agencia;
	
	@NotBlank(message = "Conta é um campo obrigatório")
	@Size(min = 6, max = 6, message = "A conta deve conter {min} digitos. ")
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
