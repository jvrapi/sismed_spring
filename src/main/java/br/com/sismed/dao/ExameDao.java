package br.com.sismed.dao;

import java.util.List;

import br.com.sismed.domain.Exame;

public interface ExameDao {
	
	void save(Exame exame);
	void update (Exame exame);
	void delete (Long id);
	
	Exame findById (Long id);
	
	List<Exame> findAll();

}
