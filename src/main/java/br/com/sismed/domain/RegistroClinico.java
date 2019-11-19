package br.com.sismed.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_registro_clinico")
public class RegistroClinico extends AbstractEntity<Long>{

	@Column(name = "data", nullable = false)
	private LocalDateTime data = LocalDateTime.now();
	
	@Column(name = "hora", nullable = false)
	private LocalDateTime hora = LocalDateTime.now();
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id", nullable = false)
	private Paciente paciente_id;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id", nullable = false)
	private Funcionario funcionario_id;
	
	@OneToOne
	@JoinColumn(name= "agendamento_id")
	private Agenda agendamento_id;
	
	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Agenda getAgendamento_id() {
		return agendamento_id;
	}

	public void setAgendamento_id(Agenda agendamento_id) {
		this.agendamento_id = agendamento_id;
	}

	public LocalDateTime getHora() {
		return hora;
	}

	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Funcionario getFuncionario_id() {
		return funcionario_id;
	}

	public void setFuncionario_id(Funcionario funcionario_id) {
		this.funcionario_id = funcionario_id;
	}

	public Paciente getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(Paciente paciente_id) {
		this.paciente_id = paciente_id;
	}
	
	
}
