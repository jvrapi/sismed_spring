package br.com.sismed.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_paciente")
public class Paciente extends AbstractEntity<Long>{

	@Column(name = "nome", nullable = false, length = 50)
	private String nome;
	
	@Column(name = "cpf", length = 15)
	private String cpf;
	
	@Column(name = "rg", length = 15)
	private String rg;
	
	@Column(name = "orgao_emissor", length = 15)
	private String orgao_emissor;
	
	@Column(name = "data_emissao", length = 15)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data_emissao;
	
	@Column(name = "telefone_fixo", length = 15)
	private String telefone_fixo;
	
	@Column(name = "telefone_trabalho", length = 15)
	private String telefone_trabalho;
	
	@Column(name = "celular", nullable = false, length = 15)
	private String celular;
	
	@Column(name = "sexo", length = 15)
	private String sexo;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_nascimento", length = 15)
	private LocalDate data_nascimento;
	
	@Column(name = "email", length = 50)
	private String email;
	
	@Column(name = "estado_civil", length = 20)
	private String estado_civil;
	
	@Column(name = "escolaridade", length = 40)
	private String escolaridade;
	
	@Column(name = "profissao", length = 50)
	private String profissao;
	
	@Column(name = "recomendacao", length = 50)
	private String recomendacao;
	
	@Column(name = "naturalidade", length = 45)
	private String naturalidade;
	
	@Column(name = "nacionalidade", length = 45)
	private String nacionalidade;
	
	@Column(name = "situacao", length = 45)
	private String situacao;
	
	@Column(name = "carteira_convenio", length = 45)
	private String carteira_convenio;

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "validade")
	private LocalDate validade;
	
	@OneToOne
	@JoinColumn(name = "tipo_convenio", nullable = false)
	private TConvenio tipo_convenio;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

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

	public LocalDate getData_emissao() {
		return data_emissao;
	}

	public void setData_emissao(LocalDate data_emissao) {
		this.data_emissao = data_emissao;
	}

	public String getTelefone_fixo() {
		return telefone_fixo;
	}

	public void setTelefone_fixo(String telefone_fixo) {
		this.telefone_fixo = telefone_fixo;
	}

	public String getTelefone_trabalho() {
		return telefone_trabalho;
	}

	public void setTelefone_trabalho(String telefone_trabalho) {
		this.telefone_trabalho = telefone_trabalho;
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

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRecomendacao() {
		return recomendacao;
	}

	public void setRecomendacao(String recomendacao) {
		this.recomendacao = recomendacao;
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

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
	public TConvenio getTipo_convenio() {
		return tipo_convenio;
	}

	public void setTipo_convenio(TConvenio tipo_convenio) {
		this.tipo_convenio = tipo_convenio;
	}

	public String getCarteira_convenio() {
		return carteira_convenio;
	}

	public void setCarteira_convenio(String carteira_convenio) {
		this.carteira_convenio = carteira_convenio;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}
