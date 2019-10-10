package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.dao.ExameDao;
import br.com.sismed.domain.Exame;

@Service
@Transactional(readOnly = false)
public class ExameServiceImp implements ExameService{

	@Autowired
	private ExameDao dao;
	
	@Override
	public void salvar(Exame exame) {
		dao.save(exame);
	}

	@Override
	public void editar(Exame exame) {
		dao.update(exame);
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Exame buscarporId(Long id) {
		
		return dao.findById(id);
	}

	@Override 
	@Transactional(readOnly = true)
	public List<Exame> buscarTodos() {
	
		return dao.findAll();
	}
}
