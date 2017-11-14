package br.com.softplan.sajadv.processos.specification;

import br.com.softplan.sajadv.processos.dto.ResponsavelFiltroDto;
import br.com.softplan.sajadv.processos.entity.Processo;
import br.com.softplan.sajadv.processos.entity.Responsavel;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResponsavelSpecification implements Specification<Responsavel> {

    private final ResponsavelFiltroDto dto;

    public ResponsavelSpecification(ResponsavelFiltroDto dto) {
        this.dto = dto;
    }

    @Override
    public Predicate toPredicate(Root<Responsavel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(dto.getNome()) && !dto.getNome().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("nome")), "%" + dto.getNome().toLowerCase() + "%"));
        }

        if (Objects.nonNull(dto.getCpf()) && !dto.getCpf().isEmpty()) {
            predicates.add(cb.equal(root.get("cpf"), dto.getCpf()));
        }

        if (Objects.nonNull(dto.getNumeroProcesso()) && !dto.getNumeroProcesso().isEmpty()) {
            Join<Responsavel, Processo> joinProcessos = root.join("processos");
            predicates.add(cb.equal(joinProcessos.get("numero"), dto.getNumeroProcesso()));
        }

        return andTogether(predicates, cb);
    }

    private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
