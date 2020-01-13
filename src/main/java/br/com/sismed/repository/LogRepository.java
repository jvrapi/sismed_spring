package br.com.sismed.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sismed.domain.Log;

public interface LogRepository extends JpaRepository<Log, Long>{

	@Query(value="call verificar_log()", nativeQuery=true)
	public void verificarLog();
	
	@Query(value="SELECT * FROM sismed_log ORDER BY data DESC, hora DESC", nativeQuery=true)
	public Page<Log> findAllLogs(Pageable pageable);
}
