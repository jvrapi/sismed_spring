package br.com.sismed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sismed.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	
}
