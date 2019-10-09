package br.com.sismed.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_custos")
public class Custos extends AbstractEntity<Long>{

	@ManyToOne
	@JoinColumn(name="agenda_id")
	private Agenda agenda;
	
	@Column(nullable = false)
	private Long prontuario;
	
	@Column(nullable = false)
	private Long convenio;
	
	@Column(nullable = false)
	private Long procedimento;
	
	@Column(nullable = false)
	private LocalDateTime data = LocalDateTime.now();
	
	@Column(nullable = false)
	private LocalDateTime hora = LocalDateTime.now();
	
	@Column(nullable = false)
	private String crm;
	
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2")
	private Double valor;
}
