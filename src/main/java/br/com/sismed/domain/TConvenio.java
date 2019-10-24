package br.com.sismed.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_tipo_convenio")
public class TConvenio extends AbstractEntity<Long>{

	@Column(nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "convenio_id") // nome da chave estrangeira no banco de dados
	private Convenio convenio;
	
	@OneToMany(mappedBy ="tconvenio")
	private List<Procedimento> procedimentos;
	

	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
	
	
	
	
	
}
