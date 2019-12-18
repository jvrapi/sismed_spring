package br.com.sismed.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.sismed.domain.Agenda;
import br.com.sismed.repository.AgendaRepository;

@Service 
public class AgendaService {

	@Autowired
	private AgendaRepository repository;
	
	@Transactional(readOnly = false)
	public void salvar(Agenda agenda) {
		repository.save(agenda);

	}

	@Transactional(readOnly = false)
	public void excluir(Long id) {
		repository.deleteById(id);
		
	}


	@Transactional(readOnly = true)
	public List<Agenda> BuscarTodos() {
		
		return repository.findAll();
	}

	

	@Transactional(readOnly = true)
	public List<Agenda> ListarAgendamentos() {
		return repository.Agendamentos();
	}

	@Transactional(readOnly = true)
	public Agenda buscarPorId(Long id) {
		return repository.getOne(id);
	}

	@Transactional(readOnly = true)
	public List<Agenda> ListarAgendamentosMedico(Long medico_id) {
		return repository.ListarAgendamentosMedico(medico_id);
	}

	@Transactional(readOnly = true)
	public List<Agenda> encerrarAtendimento() {
		
		return repository.encerrarAtendimento();
	}

	@Transactional(readOnly = true)
	public Page<Agenda> agendamentosAnteriores(Long id, Pageable pageable) {
		return repository.agendamentosAnteriores(id, pageable);
	}

	@Transactional(readOnly = true)
	public List<Agenda> buscarAgendamentos(String data, Long medico) {
		return repository.buscarAgendamentos(data, medico);
	}


	@Transactional(readOnly = true)
	public List<Agenda> ultimoAgendamento(Long id) {
		return repository.ultimoAgendamento(id);
	}





	

}
