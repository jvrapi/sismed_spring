package br.com.sismed.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_paciente")
public class Paciente extends AbstractEntity<Long>{

	@Column(name = "nome", nullable = false, length = 40)
	private String nome;
	
	@Column(name = "cpf", nullable = false, length = 11)
	private String cpf;
	
	@Column(name = "rg", nullable = false, length = 9)
	private String rg;
	
	@Column(name = "orgao_emissor", nullable = false)
	private String orgao_emissor;
	
	@Column(name = "data_emissao", nullable = false)
	private Date data_emissao;
	
	@Column(name = "telefone_fixo", nullable = false)
	private String telefone_fixo;
	
	@Column(name = "telefone_trabalho", nullable = false)
	private String telefone_trabalho;
	
	@Column(name = "celular", nullable = false)
	private String celular;
	
	@Column(name = "sexo", nullable = false)
	private String sexo;
	
	@Column(name = "cor", nullable = false)
	private String cor;
	
	@Column(name = "data_nascimento", nullable = false)
	private String data_nasimento;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "estado_civil", nullable = false)
	private String estado_civil;
	
	@Column(name = "escolaridade", nullable = false)
	private String escolaridade;
	
	@Column(name = "profissao", nullable = false)
	private String profissao;
	
	@Column(name = "recomendacao", nullable = false)
	private String recomendacao;
	
	@Column(name = "cep", nullable = false)
	private String cep;
	
	@Column(name = "endereco", nullable = false)
	private String endereco;
	
	@Column(name = "bairro", nullable = false)
	private String bairro;
	
	@Column(name = "cidade", nullable = false)
	private String cidade;
	
	@Column(name = "estado", nullable = false)
	private String estado;
	
	@Column(name = "naturalidade", nullable = false)
	private String naturalidade;
	
	@Column(name = "nacionalidade", nullable = false)
	private String nacionalidade;
	
	@Column(name = "situacao", nullable = false)
	private String situacao;
	
	@ManyToOne
	@JoinColumn(name = "convenio_id", nullable = false)
	private Convenio convenio_id;
	
	@ManyToOne
	@JoinColumn(name = "tipo_convenio_id", nullable = false)
	private TipoConvenio tipo_convenio_id;
	
	@Column(name = "carteira_convenio", nullable = false)
	private String carteira_convenio;

	@Column(name = "validade", nullable = false)
	private String validade;
	
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

	public Date getData_emissao() {
		return data_emissao;
	}

	public void setData_emissao(Date data_emissao) {
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

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getData_nasimento() {
		return data_nasimento;
	}

	public void setData_nasimento(String data_nasimento) {
		this.data_nasimento = data_nasimento;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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
	
	public Convenio getConvenio_id() {
		return convenio_id;
	}

	public void setConvenio_id(Convenio convenio_id) {
		this.convenio_id = convenio_id;
	}

	public TipoConvenio getTipo_convenio_id() {
		return tipo_convenio_id;
	}

	public void setTipo_convenio_id(TipoConvenio tipo_convenio_id) {
		this.tipo_convenio_id = tipo_convenio_id;
	}

	public String getCarteira_convenio() {
		return carteira_convenio;
	}

	public void setCarteira_convenio(String carteira_convenio) {
		this.carteira_convenio = carteira_convenio;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}
}
