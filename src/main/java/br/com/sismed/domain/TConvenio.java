package br.com.sismed.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_tipo_convenio")
public class TConvenio extends AbstractEntity{

	
	@NotBlank(message = "O nome do tipo de convênio é obrigatório")
	@Size(min = 3, max = 50, message = "O nome do tipo de convênio deve ter entre {min} e {max} caracteres. ")
	@Column(nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "convenio_id") // nome da chave estrangeira no banco de dados
	private Convenio convenio;
	
	@OneToMany(mappedBy = "tconvenio_id")
    Set<LabTConv> tconv;
	
	@OneToMany(mappedBy = "tconvenio")
    Set<FuncTConv> tconvenio;
	
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
