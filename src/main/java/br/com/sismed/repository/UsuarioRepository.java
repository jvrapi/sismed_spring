package br.com.sismed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sismed.domain.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("select u from Usuario u where u.cpf like :cpf")
	Usuario findByCpf(@Param("cpf") String cpf);
}
