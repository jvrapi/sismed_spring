package br.com.sismed.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SuppressWarnings("serial")
@Entity
@Table(name="sismed_login")
public class Login extends AbstractEntity{

	@Column(name="senha", nullable=false)
	private String senha;
	
	@Column(name="cpf", nullable=false)
	private String cpf;
	
	@OneToOne
	@JoinColumn(name = "perfil_id")
	private Perfil perfis;
	
	@OneToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario_id;
	
	@Column(name = "codigo_verificador", length = 6)
	private String codigoVerificador;
	
	public Login() {
		super();
	}

	public Login(Long id) {
		super.setId(id);
	}
	
	public Login(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Perfil getPerfis() {
		return perfis;
	}

	public void setPerfis(Perfil perfis) {
		this.perfis = perfis;
	}

	public Funcionario getFuncionario_id() {
		return funcionario_id;
	}

	public void setFuncionario_id(Funcionario funcionario_id) {
		this.funcionario_id = funcionario_id;
	}

	public String getCodigoVerificador() {
		return codigoVerificador;
	}

	public void setCodigoVerificador(String codigoVerificador) {
		this.codigoVerificador = codigoVerificador;
	}

	

}
