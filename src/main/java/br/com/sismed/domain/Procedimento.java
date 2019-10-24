package br.com.sismed.domain;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_procedimento")
public  class Procedimento extends AbstractEntity<Long> {
	
	@Column(nullable = false, length = 60)
	private String descricao;
	
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "tipo_convenio")
	private TConvenio tconvenio;
	
	@OneToMany(mappedBy = "procedimento") // nome do atributo na classe Agenda
	private List<Agenda> agenda;
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TConvenio getTconvenio() {
		return tconvenio;
	}

	public void setTconvenio(TConvenio tconvenio) {
		this.tconvenio = tconvenio;
	}

	public List<Agenda> getAgenda() {
		return agenda;
	}

	public void setAgenda(List<Agenda> agenda) {
		this.agenda = agenda;
	}
	
		
	
}
