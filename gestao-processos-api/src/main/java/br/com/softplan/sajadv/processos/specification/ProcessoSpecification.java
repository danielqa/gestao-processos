package br.com.softplan.sajadv.processos.specification;

import br.com.softplan.sajadv.processos.dto.ProcessoFiltroDto;
import br.com.softplan.sajadv.processos.entity.Processo;
import br.com.softplan.sajadv.processos.entity.Responsavel;
import br.com.softplan.sajadv.processos.enumeration.Situacao;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProcessoSpecification implements Specification<Processo> {

    private final ProcessoFiltroDto dto;

    public ProcessoSpecification(ProcessoFiltroDto dto) {
        this.dto = dto;
    }

    @Override
    public Predicate toPredicate(Root<Processo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(dto.getNumero()) && !dto.getNumero().isEmpty()) {
            predicates.add(cb.equal(root.get("numero"), dto.getNumero()));
        }

        if (Objects.nonNull(dto.getDataDistribuicaoInicio()) && Objects.nonNull(dto.getDataDistribuicaoFim())) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("dataDistribuicao"), dto.getDataDistribuicaoInicio()));
            predicates.add(cb.lessThanOrEqualTo(root.get("dataDistribuicao"), dto.getDataDistribuicaoFim()));
        }

        if (Objects.nonNull(dto.getSituacao())) {
            predicates.add(cb.equal(root.get("situacao"), Situacao.forValue(dto.getSituacao())));
        }

        predicates.add(cb.equal(root.get("segredoJustica"), dto.isSegredoJustica()));

        if (Objects.nonNull(dto.getPastaFisicaCliente()) && !dto.getPastaFisicaCliente().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("pastaFisicaCliente")), "%" + dto.getPastaFisicaCliente().toLowerCase() + "%"));
        }

        if (Objects.nonNull(dto.getNomeResponsavel()) && !dto.getNomeResponsavel().isEmpty()) {
            Join<Processo, Responsavel> joinResponsaveis = root.join("responsaveis");
            predicates.add(cb.like(cb.lower(joinResponsaveis.get("nome")), "%" + dto.getNomeResponsavel().toLowerCase() + "%"));
            query.distinct(true);
        }

        return andTogether(predicates, cb);
    }

    private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
