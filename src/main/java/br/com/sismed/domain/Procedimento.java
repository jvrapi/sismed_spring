package br.com.sismed.domain;


import java.math.BigDecimal;
import java.util.List;


import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_procedimento")
public  class Procedimento extends AbstractEntity<Long> {
	
	@Column(nullable = false, length = 60)
	private String descricao;
	
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal valor;
	
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
	
		
	@OneToMany(mappedBy = "procedimento") // nome do atributo na classe Agenda
	private List<Agenda> agenda;
}
