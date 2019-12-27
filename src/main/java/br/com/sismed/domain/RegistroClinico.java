package br.com.sismed.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_registro_clinico")
public class RegistroClinico extends AbstractEntity{

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data", nullable = false)
	private LocalDate data;
	
	@Column(name = "hora", nullable = false)
	private LocalTime hora;
	
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
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Agenda getAgendamento_id() {
		return agendamento_id;
	}

	public void setAgendamento_id(Agenda agendamento_id) {
		this.agendamento_id = agendamento_id;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
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

	public Boolean compararDatas(LocalDate rclinicoData) {
		LocalDate dataAtual = LocalDate.now();
		return rclinicoData.isBefore(dataAtual);
	}
}
