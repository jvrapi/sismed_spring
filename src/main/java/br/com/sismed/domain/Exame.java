package br.com.sismed.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@JoinColumn(name = "paciente_id") 
	private Paciente paciente_id;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario_id;
	
	

	/*@ManyToMany
	@JoinTable(
		name = "sismed_exame_laboratorio", 
		joinColumns = @JoinColumn (
		name = "exame_id"), inverseJoinColumns = 
		@JoinColumn (name = "laboratorio_id")
		)
	
	private List<Laboratorio> laboratorio;
	
	public List<Laboratorio> getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(List<Laboratorio> laboratorio) {
		this.laboratorio = laboratorio;
	}*/

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

	public Paciente getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(Paciente paciente_id) {
		this.paciente_id = paciente_id;
	}

	public Funcionario getFuncionario_id() {
		return funcionario_id;
	}

	public void setFuncionario_id(Funcionario funcionario_id) {
		this.funcionario_id = funcionario_id;
	}



	
}
