package br.com.sismed.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_laboratorio_tconvenio")
public class LabTConv {

	@Id
	@Column(name = "sismed_tipo_convenio_id")
	private Long tconvenio_id;
	
	@Column(name = "sismed_laboratorio_id")
	private Long laboratorio_id;

	public Long getTconvenio_id() {
		return tconvenio_id;
	}

	public void setTconvenio_id(Long tconvenio_id) {
		this.tconvenio_id = tconvenio_id;
	}

	public Long getLaboratorio_id() {
		return laboratorio_id;
	}

	public void setLaboratorio_id(Long laboratorio_id) {
		this.laboratorio_id = laboratorio_id;
	}
}
