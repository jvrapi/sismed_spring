package br.com.sismed.domain;

import java.time.LocalDateTime;

import java.util.List;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name="sismed_agenda")
public  class Agenda extends AbstractEntity<Long> {
	
	@ManyToOne
	@JoinColumn(name = "paciente_id") // nome da chave estrangeira no banco de dados
	private Paciente paciente_id;
	
	@ManyToOne
	@JoinColumn(name = "procedimento_id") // nome da chave estrangeira no banco de dados
	private Procedimento procedimento;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id") // nome da chave estrangeira no banco de dados
	private Funcionario funcionario;
	
	@Column(nullable = false)
	private String observacao;
	
	@Column(nullable = false)
	private String compareceu;
	
	@Column(nullable = false)
	private String primeira_vez;
	
	@Column(nullable = false)
	private String pagou;
	
	@Column(nullable = false)
	private LocalDateTime data =  LocalDateTime.now();
	
	@Column(nullable = false)
	private LocalDateTime hora =  LocalDateTime.now();

	
	@OneToMany(mappedBy = "agenda") // nome do atributo na classe Custos
	private List<Custos> custos;

	
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

	public String getCompareceu() {
		return compareceu;
	}

	public void setCompareceu(String compareceu) {
		this.compareceu = compareceu;
	}

	public String getPagou() {
		return pagou;
	}

	public void setPagou(String pagou) {
		this.pagou = pagou;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public LocalDateTime getHora() {
		return hora;
	}

	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getPrimeira_vez() {
		return primeira_vez;
	}

	public void setPrimeira_vez(String primeira_vez) {
		this.primeira_vez = primeira_vez;
	}

	public List<Custos> getCustos() {
		return custos;
	}

	public void setCustos(List<Custos> custos) {
		this.custos = custos;
	}
	
	
}
