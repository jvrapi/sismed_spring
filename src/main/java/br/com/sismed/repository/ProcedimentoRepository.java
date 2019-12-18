package br.com.sismed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


import br.com.sismed.domain.Procedimento;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long>{

	@Query(value = "SELECT p.id, p.descricao, p.valor, p.convenio_id FROM sismed_convenio c INNER JOIN sismed_procedimento p ON c.id = p.convenio_id WHERE c.id =  "
			+ ":id ORDER BY p.descricao ", nativeQuery=true)
	List<Procedimento> ListarProcedimento(Long id);
	
	@Query(value = "SELECT * FROM sismed_procedimento p WHERE p.descricao LIKE %:dado% AND convenio_id = :convenio", nativeQuery=true)
	List<Procedimento> ListarPorDescricao(String dado, Long convenio);
}
