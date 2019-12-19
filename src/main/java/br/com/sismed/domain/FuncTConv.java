package br.com.sismed.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_funcionario_tconvenio")
public class FuncTConv extends AbstractEntity{
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name = "tipo_convenio_id")
	private TConvenio tconvenio;
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public TConvenio getTconvenio() {
		return tconvenio;
	}
	public void setTconvenio(TConvenio tconvenio) {
		this.tconvenio = tconvenio;
	}
	

}
