package br.com.sismed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sismed.domain.LabTConv;

public interface LabTConvRepository extends JpaRepository<LabTConv, Long>{
	
}
