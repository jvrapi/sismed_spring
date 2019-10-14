package br.com.sismed.domain;



import java.util.List;


import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_tipo_convenio")
public abstract class TipoConvenio extends AbstractEntity<Long> {
	
	
	@Column(name="nome",nullable = false, length = 60, unique = true)
	private String nome;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "convenio_id")
	
	private Convenio convenio = new Convenio();

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
	
	
	
	@OneToMany(mappedBy = "tipo_convenio") // nome do atributo na classe Agenda
	private List<Agenda> agenda;
}
