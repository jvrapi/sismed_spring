package br.com.sismed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sismed.domain.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
