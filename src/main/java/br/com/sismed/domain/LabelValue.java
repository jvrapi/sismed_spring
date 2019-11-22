package br.com.sismed.domain;

public class LabelValue {
	private Long convenio;
	private Long tipo;
	private Long value;
	private String label;
	private String nome_tipo;
	private String nome_convenio;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public Long getConvenio() {
		return convenio;
	}
	public void setConvenio(Long convenio) {
		this.convenio = convenio;
	}
	public Long getTipo() {
		return tipo;
	}
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
	public String getNome_tipo() {
		return nome_tipo;
	}
	public void setNome_tipo(String nome_tipo) {
		this.nome_tipo = nome_tipo;
	}
	public String getNome_convenio() {
		return nome_convenio;
	}
	public void setNome_convenio(String nome_convenio) {
		this.nome_convenio = nome_convenio;
	}
}
