package br.com.sismed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sismed.domain.Convenio;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Long>{

}
