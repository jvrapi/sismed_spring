package br.com.sismed.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name="sismed_funcionario")
public class Funcionario extends AbstractEntity<Long> {
	
	@Column(name="nome", nullable=false, length=40)
	private String nome;
	
	@Column(name="cpf", nullable=false, length=12)
	private String cpf;
	
	@Column(name="rg", nullable=false, length=10)
	private String rg;
	
	@Column(name="orgao_emissor", nullable=false, length=30)
	private String orgao_emissor;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name="data_emissao", nullable=false, length=10)
	private LocalDate data_emissao;
	
	@Column(name="crm", nullable=true, length=10)
	private String crm;
	
	@Column(name="especialidade", nullable=true, length=45)
	private String especialidade;
	
	@Column(name="telefone", nullable=false, length=15)
	private String telefone;
	
	@Column(name="celular", nullable=false, length=15)
	private String celular;
	
	@Column(name="sexo", nullable=false, length=15)
	private String sexo;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name="data_nascimento", nullable=false, length=10)
	private LocalDate data_nascimento;
	
	@Column(name="email", nullable=false, length=30)
	private String email;
	
	@Column(name="estado_civil", nullable=false, length=20)
	private String estado_civil;
	
	@Column(name="escolaridade", nullable=false, length=45)
	private String escolaridade;
	
	@Column(name="formacao", nullable=false, length=45)
	private String formacao;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@Column(name="naturalidade", nullable=false, length=45)
	private String naturalidade;
	
	@Column(name="nacionalidade", nullable=false, length=20)
	private String nacionalidade;
	
	@Column(name="nivel_acesso", nullable=false, length=4)
	private Long nivel_acesso;
	
	
	public LocalDate getData_emissao() {
		return data_emissao;
	}
	public void setData_emissao(LocalDate data_emissao) {
		this.data_emissao = data_emissao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getOrgao_emissor() {
		return orgao_emissor;
	}
	public void setOrgao_emissor(String orgao_emissor) {
		this.orgao_emissor = orgao_emissor;
	}
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public LocalDate getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEstado_civil() {
		return estado_civil;
	}
	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}
	public String getEscolaridade() {
		return escolaridade;
	}
	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}
	public String getFormacao() {
		return formacao;
	}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public Long getNivel_acesso() {
		return nivel_acesso;
	}
	public void setNivel_acesso(Long nivel_acesso) {
		this.nivel_acesso = nivel_acesso;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}
