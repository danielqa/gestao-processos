package br.com.softplan.sajadv.processos.service;

import br.com.softplan.sajadv.processos.app.Constants;
import br.com.softplan.sajadv.processos.app.Messages;
import br.com.softplan.sajadv.processos.app.Result;
import br.com.softplan.sajadv.processos.dto.ResponsavelFiltroDto;
import br.com.softplan.sajadv.processos.entity.Processo;
import br.com.softplan.sajadv.processos.entity.Responsavel;
import br.com.softplan.sajadv.processos.repository.ResponsavelRepository;
import br.com.softplan.sajadv.processos.specification.ResponsavelSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Service
public class ResponsavelService {

    @Inject
    private Result result;

    @Inject
    private ResponsavelRepository repository;

    @Transactional(readOnly = true)
    public List<Responsavel> findAll() {
        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Responsavel> findByFilters(final int pageNumber, final ResponsavelFiltroDto dto) {
        return this.repository.findAll(
                new ResponsavelSpecification(dto),
                new PageRequest(pageNumber > 0 ? pageNumber - 1 : 0, Constants.PAGINATION_PAGE_SIZE));
    }

    @Transactional(readOnly = true)
    public Responsavel get(final Serializable id) {
        return this.repository.findOne(id);
    }

    @Transactional
    public void delete(final Serializable id) {
        this.repository.delete(id);
        this.repository.flush();
        this.result.addMessage(Messages.REMOVIDO_SUCESSO);
    }

    @Transactional
    public void save(final Responsavel responsavel) {
        this.repository.save(responsavel);
        this.result.addMessage(Messages.GRAVADO_SUCESSO);
    }

    @Transactional(readOnly = true)
    public Set<Processo> getProcessos(final Serializable id) {
        if (this.repository.exists(id)) {
            return this.get(id).getProcessos();
        }
        return null;
    }
}
