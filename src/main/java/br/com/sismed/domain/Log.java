package br.com.sismed.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name="sismed_log")
public class Log extends AbstractEntity{
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name="data", nullable=false)
	private LocalDate data;
	
	@DateTimeFormat(iso = ISO.TIME)
	@Column(name="hora", nullable=false)
	private LocalDate hora;

	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario_id;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalDate getHora() {
		return hora;
	}

	public void setHora(LocalDate hora) {
		this.hora = hora;
	}

	public Funcionario getFuncionario_id() {
		return funcionario_id;
	}

	public void setFuncionario_id(Funcionario funcionario_id) {
		this.funcionario_id = funcionario_id;
	}
}
