package br.com.sismed.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.sismed.domain.Agenda;
import br.com.sismed.repository.AgendaRepository;

@Service @Transactional(readOnly = false)
public class AgendaServiceImpl implements AgendaService{

	@Autowired
	private AgendaRepository dao;
	
	@Override
	public void salvar(Agenda agenda) {
		dao.save(agenda);
		
	}

	@Override
	public void excluir(Long id) {
		dao.deleteById(id);
		
	}


	@Override
	public List<Agenda> BuscarTodos() {
		
		return dao.findAll();
	}

	

	@Override
	public List<Agenda> ListarAgendamentos() {
		return dao.Agendamentos();
	}

	@Override
	public Agenda buscarPorId(Long id) {
		return dao.getOne(id);
	}

	

}
