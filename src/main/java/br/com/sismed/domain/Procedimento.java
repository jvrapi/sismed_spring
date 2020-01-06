package br.com.sismed.domain;


import java.math.BigDecimal;



import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_procedimento")
public  class Procedimento extends AbstractEntity {
	
	@Column(nullable = false, length = 60)
	private String descricao;
	
	
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "convenio_id")
	private Convenio convenio;
	
	
	
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

	

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	
	
		
	
}
