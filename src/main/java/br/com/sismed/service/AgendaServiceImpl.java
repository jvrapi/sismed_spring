package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.dao.AgendaDao;
import br.com.sismed.domain.Agenda;

@Service @Transactional(readOnly = false)
public class AgendaServiceImpl implements AgendaService{

	@Autowired
	private AgendaDao dao;
	
	@Override
	public void salvar(Agenda agenda) {
		dao.save(agenda);
		
	}

	@Override
	public void editar(Agenda agenda) {
		dao.update(agenda);
		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override
	public Agenda buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override
	public List<Agenda> BuscarTodos() {
		
		return dao.findAll();
	}

}
