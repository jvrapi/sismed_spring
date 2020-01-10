package br.com.sismed.domain;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SuppressWarnings("serial")
@Entity
@Table(name="sismed_agenda")
public  class Agenda extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(name = "paciente_id") // nome da chave estrangeira no banco de dados
	private Paciente paciente_id;
	
	@ManyToOne
	@JoinColumn(name = "procedimento_id") // nome da chave estrangeira no banco de dados
	private Procedimento procedimento;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "funcionario_id") // nome da chave estrangeira no banco de dados
	private Funcionario funcionario;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tipo_convenio_id") // nome da chave estrangeira no banco de dados
	private TConvenio tipo_convenio;
	
	@Column(nullable = false)
	private String observacao;
	
	@Column(nullable = false)
	private Long compareceu;
	
	@Column(nullable = false)
	private Long primeira_vez;
	
	@Column(nullable = false)
	private Long pagou;
	
	@Column(nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data ;
	
	@Column(nullable = false)
	@DateTimeFormat(iso = ISO.TIME)
	private LocalTime hora;

	
	

	
	public Paciente getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(Paciente paciente_id) {
		this.paciente_id = paciente_id;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getObservavao() {
		return observacao;
	}

	public void setObservavao(String observacao) {
		this.observacao = observacao;
	}

	public Long getCompareceu() {
		return compareceu;
	}

	public void setCompareceu(Long compareceu) {
		this.compareceu = compareceu;
	}

	public Long getPagou() {
		return pagou;
	}

	public void setPagou(Long pagou) {
		this.pagou = pagou;
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Long getPrimeira_vez() {
		return primeira_vez;
	}

	public void setPrimeira_vez(Long primeira_vez) {
		this.primeira_vez = primeira_vez;
	}

	

	public TConvenio getTipo_convenio() {
		return tipo_convenio;
	}

	public void setTipo_convenio(TConvenio tipo_convenio) {
		this.tipo_convenio = tipo_convenio;
	}
	
	
	public Boolean compararDatas(LocalDate agendamento) {
		
		LocalDate dataAtual = LocalDate.now();
		
		
		return agendamento.isBefore(dataAtual);
		
		
		
	}
	
}
