package br.com.sismed.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sismed_laboratorio")
public class Laboratorio extends AbstractEntity<Long>{
	
	@Column(name="nome", nullable=false, length=30)
	private String nome;
	
	@Column(name="responsavel", nullable=false, length=45)
	private String responsavel;
	
	@Column(name="telefone", nullable=false, length=40)
	private String telefone;
	
	@Column(name="email", nullable=false, length=15)
	private String email;
	
	@Column(name="logradouro", nullable=false, length=45)
	private String logradouro;
	
	@Column(name="numero", nullable=false, length=4)
	private String numero;
	
	@Column(name="bairro", nullable=false, length=45)
	private String bairro;
	
	@Column(name="cidade", nullable=false, length=45)
	private String cidade;
	
	@Column(name="estado", nullable=false, length=2)
	private String estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
