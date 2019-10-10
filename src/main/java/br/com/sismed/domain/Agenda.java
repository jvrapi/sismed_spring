package br.com.sismed.domain;

import java.time.LocalDateTime;

import java.util.List;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name="sismed_agenda")
public abstract class Agenda extends AbstractEntity<Long> {
	
	@ManyToOne
	@JoinColumn(name = "paciente_prontuario") // nome da chave estrangeira no banco de dados
	private Paciente paciente_prontuario;
	
	@ManyToOne 
	@JoinColumn(name = "convenio") // nome da chave estrangeira no banco de dados
	private Convenio convenio;
	
	@ManyToOne
	@JoinColumn(name = "tipo_convenio_id") // nome da chave estrangeira no banco de dados
	private TipoConvenio tipo_convenio;
	
	@ManyToOne
	
	@JoinColumn(name = "procedimento_id") // nome da chave estrangeira no banco de dados
	private Procedimento procedimento;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id") // nome da chave estrangeira no banco de dados
	private Funcionario funcionario;
	
	@Column(nullable = false)
	private String observavao;
	
	@Column(nullable = false)
	private String compareceu;
	
	@Column(nullable = false)
	private String pagou;
	
	@Column(nullable = false)
	private LocalDateTime data =  LocalDateTime.now();
	
	@Column(nullable = false)
	private LocalDateTime hora =  LocalDateTime.now();

	
	@OneToMany(mappedBy = "agenda") // nome do atributo na classe Custos
	private List<Custos> custos;
	
	public Paciente getPaciente_prontuario() {
		return paciente_prontuario;
	}

	public void setPaciente_prontuario(Paciente paciente_prontuario) {
		this.paciente_prontuario = paciente_prontuario;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public TipoConvenio getTipo_convenio() {
		return tipo_convenio;
	}

	public void setTipo_convenio(TipoConvenio tipo_convenio) {
		this.tipo_convenio = tipo_convenio;
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
		return observavao;
	}

	public void setObservavao(String observavao) {
		this.observavao = observavao;
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
	
	
}
