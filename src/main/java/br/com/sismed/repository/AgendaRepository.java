package br.com.sismed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


import br.com.sismed.domain.Agenda;


public interface  AgendaRepository extends JpaRepository<Agenda, Long>{
	@Query(value = "SELECT * FROM sismed_agenda  WHERE data = (CURDATE()) ORDER BY hora ASC", nativeQuery=true)
	List<Agenda> Agendamentos();
	
	
	@Query(value = "SELECT * FROM sismed_agenda  WHERE data = (CURDATE()) AND funcionario_id = :medico_id ORDER BY hora ASC", nativeQuery=true)
	List<Agenda> ListarAgendamentosMedico(Long medico_id);
}
