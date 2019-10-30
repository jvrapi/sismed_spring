package br.com.sismed.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="sismed_laboratorio")
public class Laboratorio extends AbstractEntity<Long>{
	
	@Column(name="cnpj", nullable=false, length=5)
	private String cnpj;
	
	@Column(name="nome", nullable=false, length=30)
	private String nome;
	
	@Column(name="responsavel", nullable=false, length=40)
	private String responsavel;
	
	@Column(name="telefone", nullable=false, length=15)
	private String telefone;
	
	@Column(name="email", nullable=false, length=30)
	private String email;
	
	@Column(name="endereco", nullable=false, length=45)
	private String endereco;
	
	@Column(name="bairro", nullable=false, length=30)
	private String bairro;
	
	@Column(name="cidade", nullable=false, length=45)
	private String cidade;
	
	@Column(name="estado", nullable=false, length=2)
	private String estado;
	
	/*@ManyToMany(mappedBy = "laboratorio")
	
	Laboratorio l = l1;
	List<Exame> ex = l.getExame();
	
	Laboratorio l1
	
	private List<Exame> exame;
	
	public List<Exame> getExame() {
		return exame;
	}

	public void setExame(List<Exame> exame) {
		this.exame = exame;
	}*/

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
}
