package br.com.sismed.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.sismed.domain.Agenda;
import br.com.sismed.repository.AgendaRepository;

@Service @Transactional(readOnly = false)
public class AgendaService {

	@Autowired
	private AgendaRepository dao;
	
	
	public void salvar(Agenda agenda) {
		dao.save(agenda);
		
	}

	
	public void excluir(Long id) {
		dao.deleteById(id);
		
	}


	
	public List<Agenda> BuscarTodos() {
		
		return dao.findAll();
	}

	

	
	public List<Agenda> ListarAgendamentos() {
		return dao.Agendamentos();
	}

	
	public Agenda buscarPorId(Long id) {
		return dao.getOne(id);
	}


	public List<Agenda> ListarAgendamentosMedico(Long medico_id) {
		return dao.ListarAgendamentosMedico(medico_id);
	}




	

}
