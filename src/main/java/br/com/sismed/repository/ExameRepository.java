package br.com.sismed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sismed.domain.Exame;

public interface ExameRepository extends JpaRepository<Exame, Long> {

}
