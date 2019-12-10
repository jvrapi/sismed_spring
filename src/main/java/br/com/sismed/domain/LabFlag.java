package br.com.sismed.domain;

import java.util.List;

public class LabFlag {

	List<Laboratorio> listLab;
	int flag;
	
	public List<Laboratorio> getListLab() {
		return listLab;
	}
	public void setListLab(List<Laboratorio> listLab) {
		this.listLab = listLab;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
