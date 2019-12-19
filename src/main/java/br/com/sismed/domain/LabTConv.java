package br.com.sismed.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_laboratorio_tconvenio")
public class LabTConv extends AbstractEntity{

	@ManyToOne
	@JoinColumn(name = "sismed_tipo_convenio_id")
	private TConvenio tconvenio_id;
	
	@ManyToOne
	@JoinColumn(name = "sismed_laboratorio_id")
	private Laboratorio laboratorio_id;

	public TConvenio getTconvenio_id() {
		return tconvenio_id;
	}

	public void setTconvenio_id(TConvenio tconvenio_id) {
		this.tconvenio_id = tconvenio_id;
	}

	public Laboratorio getLaboratorio_id() {
		return laboratorio_id;
	}

	public void setLaboratorio_id(Laboratorio laboratorio_id) {
		this.laboratorio_id = laboratorio_id;
	}

	
}
