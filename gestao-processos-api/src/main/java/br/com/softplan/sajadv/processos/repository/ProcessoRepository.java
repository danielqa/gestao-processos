package br.com.softplan.sajadv.processos.repository;

import br.com.softplan.sajadv.processos.entity.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Serializable>, JpaSpecificationExecutor<Processo> {

    @Query(value = "select p2 from Processo p1 right join p1.processoPai p2 where p1.processoPai is null")
    List<Processo> findProcessosVinculaveis();

    @Query(value = "with recursive hierarquia_abaixo as                         " +
            "(                                                                  " +
            "select id from processo where id = ?1                              " +
            "UNION ALL                                                          " +
            "select p.id from processo p                                        " +
            "JOIN hierarquia_abaixo on p.id_processo_pai = hierarquia_abaixo.id " +
            ")                                                                  " +
            "select id from hierarquia_abaixo                                   ", nativeQuery = true)
    List<BigInteger> findHierarquiaNiveisAbaixo(final Serializable id);

    @Query(value = "with recursive hierarquia_acima as                          " +
            "(                                                                  " +
            "select id, id_processo_pai from processo where id = ?1             " +
            "UNION ALL                                                          " +
            "select p.id, p.id_processo_pai from processo p                     " +
            "JOIN hierarquia_acima on p.id = hierarquia_acima.id_processo_pai   " +
            ")                                                                  " +
            "select id from hierarquia_acima                                    ", nativeQuery = true)
    List<BigInteger> findHierarquiaNiveisAcima(final Serializable id);

}
