package br.com.sismed.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_exame")
public class Exame extends AbstractEntity<Long>{
	
	@Column(name = "descricao",nullable = false, length=30)
	private String descricao;
	
	@Column(name = "data_coleta",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_coleta = new Date();
	
	@Column(name = "data_envio",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_envio = new Date();
	
	@Column(name = "data_retorno",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_retorno = new Date();
	
	@JoinColumn(name = "paciente_prontuario") 
	private Paciente paciente_prontuario;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario_id;
	
	@ManyToOne
	@JoinColumn(name = "convenio_id")
	private Convenio convenio_id;
	
	@ManyToOne
	@JoinColumn(name = "tipo_id")
	private TipoConvenio tipo_id;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData_coleta() {
		return data_coleta;
	}

	public void setData_coleta(Date data_coleta) {
		this.data_coleta = data_coleta;
	}

	public Date getData_envio() {
		return data_envio;
	}

	public void setData_envio(Date data_envio) {
		this.data_envio = data_envio;
	}

	public Date getData_retorno() {
		return data_retorno;
	}

	public void setData_retorno(Date data_retorno) {
		this.data_retorno = data_retorno;
	}

	public Paciente getPaciente_prontuario() {
		return paciente_prontuario;
	}

	public void setPaciente_prontuario(Paciente paciente_prontuario) {
		this.paciente_prontuario = paciente_prontuario;
	}

	public Funcionario getFuncionario_id() {
		return funcionario_id;
	}

	public void setFuncionario_id(Funcionario funcionario_id) {
		this.funcionario_id = funcionario_id;
	}

	public Convenio getConvenio_id() {
		return convenio_id;
	}

	public void setConvenio_id(Convenio convenio_id) {
		this.convenio_id = convenio_id;
	}

	public TipoConvenio getTipo_id() {
		return tipo_id;
	}

	public void setTipo_id(TipoConvenio tipo_id) {
		this.tipo_id = tipo_id;
	}
	
	
}
