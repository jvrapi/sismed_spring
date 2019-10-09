package br.com.sismed.domain;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_tipo_convenio")
public abstract class TipoConvenio extends AbstractEntity<Long> {
	
	
	@Column(name="nome",nullable = false, length = 60, unique = true)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "convenio_id")
	private Convenio convenio;

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
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "sismed_tipo_convenio",
            joinColumns = { @JoinColumn(name = "tipo_convenio_id") },
            inverseJoinColumns = { @JoinColumn(name = "procedimento_id") })
	private Set<Procedimento> procedimento = new HashSet<Procedimento>();
	
	@OneToMany(mappedBy = "tipo_convenio") // nome do atributo na classe Agenda
	private List<Agenda> agenda;
}
