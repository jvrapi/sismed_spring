package br.com.sismed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sismed.domain.Funcionario;
import br.com.sismed.domain.Log;
import br.com.sismed.domain.Login;

public interface LogRepository extends JpaRepository<Log, Long>{

	@Query(value="call verificar_log()", nativeQuery=true)
	public void verificarLog();
}
