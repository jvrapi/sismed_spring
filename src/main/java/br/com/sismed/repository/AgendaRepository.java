package br.com.sismed.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


import br.com.sismed.domain.Agenda;


public interface  AgendaRepository extends JpaRepository<Agenda, Long>{
	@Query(value = "SELECT * FROM sismed_agenda  WHERE data = (CURDATE()) ORDER BY hora ASC", nativeQuery=true)
	List<Agenda> Agendamentos();
	
	
	@Query(value = "SELECT * FROM sismed_agenda  WHERE data = (CURDATE()) AND funcionario_id = :medico_id ORDER BY hora ASC", nativeQuery=true)
	List<Agenda> ListarAgendamentosMedico(Long medico_id);

	@Query(value = "SELECT * FROM sismed_agenda  WHERE data = CURDATE()   ", nativeQuery=true)
	List<Agenda> encerrarAtendimento();

	@Query(value = "SELECT * FROM sismed_agenda  WHERE paciente_id = :id ORDER BY data DESC, hora  ", nativeQuery=true)
	Page<Agenda> agendamentosAnteriores(Long id, Pageable pageable);


	@Query(value = "SELECT * FROM sismed_agenda  WHERE data = :data AND funcionario_id = :medico ORDER BY hora ASC ", nativeQuery=true)
	List<Agenda> buscarAgendamentos(String data, Long medico);
	
	@Query(value = "SELECT * FROM sismed_agenda where paciente_id =  :id ORDER BY id DESC limit 1; ", nativeQuery=true)
	Agenda ultimoAgendamento(Long id);
	
}
