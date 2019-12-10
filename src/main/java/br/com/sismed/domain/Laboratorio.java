package br.com.sismed.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="sismed_laboratorio")
public class Laboratorio extends AbstractEntity{
	
	@Column(name="cnpj", length=5)
	private String cnpj;
	
	@Column(name="nome", nullable=false, length=30)
	private String nome;
	
	@Column(name="responsavel", nullable=false, length=40)
	private String responsavel;
	
	@Column(name="telefone_fixo", nullable=false, length=15)
	private String telefone_fixo;
	
	@Column(name="email", nullable=false, length=30)
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "laboratorio_id")
    Set<LabTConv> lab;
	
	@ManyToMany
	@JoinTable(name = "sismed_laboratorio_tconvenio",
	joinColumns = @JoinColumn(name = "id"),
	inverseJoinColumns = @JoinColumn(name = "id"))
	public Set<Laboratorio> laboratorio;
	
	private int flag;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

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

	public String getTelefone_fixo() {
		return telefone_fixo;
	}

	public void setTelefone_fixo(String telefone_fixo) {
		this.telefone_fixo = telefone_fixo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
