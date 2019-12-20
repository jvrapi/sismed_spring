package br.com.sismed.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_custos")
public class Custos extends AbstractEntity{

	@OneToOne
	@JoinColumn(name="agendamento")
	private Agenda agendamento;
	
	@OneToOne
	@JoinColumn(name="paciente")
	private Paciente paciente;
	
	@OneToOne
	@JoinColumn(name="convenio")
	private Convenio convenio;
	
	@OneToOne
	@JoinColumn(name="procedimento")
	private Procedimento procedimento;
	
	@OneToOne
	@JoinColumn(name="funcionario")
	private Funcionario funcionario;
	
	@Column(nullable = false)
	private LocalDate data ;
	
	@Column(nullable = false)
	private LocalTime hora;
	

	@Column(nullable = false, columnDefinition = "DECIMAL(7,2")
	private BigDecimal valor;

	public Agenda getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agenda agendamento) {
		this.agendamento = agendamento;
	}

	

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}


	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	
	
	
	
}
