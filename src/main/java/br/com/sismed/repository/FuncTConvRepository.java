package br.com.sismed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sismed.domain.FuncTConv;

@Repository
public interface FuncTConvRepository extends JpaRepository<FuncTConv, Long>{

}
