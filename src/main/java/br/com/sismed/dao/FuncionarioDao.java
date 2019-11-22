package br.com.sismed.dao;

import java.util.List;

import br.com.sismed.domain.Funcionario;

public interface FuncionarioDao {
	
	void save(Funcionario funcionario);
	void update (Funcionario funcionario);
	void delete (Long id);
	
	Funcionario findById (Long id);
	
	List<Funcionario> findAll();
	
	List<Funcionario> ListarFuncionarioId(String dado);

}
