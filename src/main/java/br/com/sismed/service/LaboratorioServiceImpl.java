package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.dao.LaboratorioDao;
import br.com.sismed.domain.Laboratorio;

@Transactional(readOnly = false)
public class LaboratorioServiceImpl implements LaboratorioService{

	@Autowired
	private LaboratorioDao dao;
	
	@Override
	public void salvar(Laboratorio laboratorio) {
	 dao.save(laboratorio);
	}

	@Override
	public void editar(Laboratorio laboratorio) {
		dao.update(laboratorio);
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Laboratorio buscarporId(Long id) {
		
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Laboratorio> buscarTodos() {
		
		return dao.findAll();
	}

	
}
