package br.com.softplan.sajadv.processos.repository;

import br.com.softplan.sajadv.processos.entity.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Serializable>, JpaSpecificationExecutor<Responsavel> {
}
