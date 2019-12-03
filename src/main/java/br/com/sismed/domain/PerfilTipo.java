package br.com.sismed.domain;

public enum PerfilTipo {

	MEDICO(1, "MEDICO"), FUNCIONARIO(2, "FUNCIONARIO");
	
	private long cod;
	private String desc;

	private PerfilTipo(long cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public long getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}
}
