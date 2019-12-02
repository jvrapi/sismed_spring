package br.com.sismed.domain;

public enum PerfilTipo {

	ADMIN(1, "ADMIN"), MEDICO(2, "MEDICO"), FUNCIONARIO(3, "FUNCIONARIO");
	
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
