package br.com.sismed.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_tipo_convenio")
public class TipoConvenio  extends AbstractEntity<Long>{


	@Column(name="nome")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="convenio_id")
	private Convenio convenio_id;

	@OneToMany(mappedBy="tipo_convenio")
	private List<Agenda> agenda;
	
	@OneToMany(mappedBy="tipo_convenio_id")
	private List<Paciente> paciente;
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Convenio getConvenio_Id() {
		return convenio_id;
	}

	public void setConvenio_Id(Convenio convenio_id) {
		this.convenio_id = convenio_id;
	}

	public List<Agenda> getAgenda() {
		return agenda;
	}

	public void setAgenda(List<Agenda> agenda) {
		this.agenda = agenda;
	}

	public List<Paciente> getPaciente() {
		return paciente;
	}

	public void setPaciente(List<Paciente> paciente) {
		this.paciente = paciente;
	}
	
}
