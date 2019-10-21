package br.com.sismed.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_exame")
public class Exame extends AbstractEntity<Long>{
	
	@Column(name = "descricao",nullable = false, length=30)
	
	private String descricao;
	
	@Column(name = "data_coleta",nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data_coleta;
	
	@Column(name = "data_envio",nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data_envio;
	
	@Column(name = "data_retorno",nullable = true)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data_retorno;
	
	@ManyToOne
	@JoinColumn(name = "paciente_prontuario") 
	private Paciente paciente_prontuario;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario_id;
	
	@ManyToOne
	@JoinColumn(name = "tipo_id")
	private TConvenio tipo_id;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getData_coleta() {
		return data_coleta;
	}

	public void setData_coleta(LocalDate data_coleta) {
		this.data_coleta = data_coleta;
	}

	public LocalDate getData_envio() {
		return data_envio;
	}

	public void setData_envio(LocalDate data_envio) {
		this.data_envio = data_envio;
	}

	public LocalDate getData_retorno() {
		return data_retorno;
	}

	public void setData_retorno(LocalDate data_retorno) {
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

	public TConvenio getTipo_id() {
		return tipo_id;
	}

	public void setTipo_id(TConvenio tipo_id) {
		this.tipo_id = tipo_id;
	}

	
}
