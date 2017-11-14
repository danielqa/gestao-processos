package br.com.softplan.sajadv.processos.service;

import br.com.softplan.sajadv.processos.app.Constants;
import br.com.softplan.sajadv.processos.app.Messages;
import br.com.softplan.sajadv.processos.app.Result;
import br.com.softplan.sajadv.processos.dto.ProcessoFiltroDto;
import br.com.softplan.sajadv.processos.entity.Processo;
import br.com.softplan.sajadv.processos.exception.ResultException;
import br.com.softplan.sajadv.processos.repository.ProcessoRepository;
import br.com.softplan.sajadv.processos.specification.ProcessoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Service
public class ProcessoService {

    @Inject
    private Result result;

    @Inject
    private ProcessoRepository repository;

    @Transactional(readOnly = true)
    public Page<Processo> findByFilters(final int pageNumber, final ProcessoFiltroDto dto) {
        return this.repository.findAll(
                new ProcessoSpecification(dto),
                new PageRequest(pageNumber > 0 ? pageNumber - 1 : 0, Constants.PAGINATION_PAGE_SIZE));
    }

    @Transactional(readOnly = true)
    public List<Processo> findProcessosVinculaveis(final Serializable id) {
        final List<Processo> processosVinculaveis = this.repository.findProcessosVinculaveis();

        if (Objects.nonNull(id) && this.repository.exists(id)) {
            final Processo processo = this.get(id);
            if (Objects.nonNull(processo.getProcessoPai()) && Objects.nonNull(processo.getProcessoPai().getId())) {
                processosVinculaveis.add(0, processo.getProcessoPai());
            }
            processosVinculaveis.remove(processo);
        }

        return processosVinculaveis;
    }

    @Transactional(readOnly = true)
    public Processo get(final Serializable id) {
        return this.repository.findOne(id);
    }

    @Transactional
    public void delete(final Serializable id) {
        beforeDelete(id);
        this.repository.delete(id);
        this.repository.flush();
        this.result.addMessage(Messages.REMOVIDO_SUCESSO);
    }

    @Transactional
    public void save(final Processo processo) {
        beforeSave(processo);
        this.repository.save(processo);
        this.result.addMessage(Messages.GRAVADO_SUCESSO);
    }

    private void beforeDelete(final Serializable id) {
        if (this.repository.exists(id) && this.get(id).getSituacao().isFinalizado()) {
            throw new ResultException(Messages.PROCESSO_FINALIZADO);
        }
    }

    private void beforeSave(final Processo processo) {
        if (Objects.nonNull(processo.getProcessoPai()) && Objects.nonNull(processo.getProcessoPai().getId())) {

            final List<BigInteger> hierarquiaNiveisAcima = this.repository.findHierarquiaNiveisAcima(processo.getProcessoPai().getId());

            validarMaximoNiveisHierarquia(hierarquiaNiveisAcima);

            if (Objects.nonNull(processo.getId())) {

                validarSeProcessoEstaNaMesmaHierarquia(hierarquiaNiveisAcima, BigInteger.valueOf(processo.getId()));

                final List<BigInteger> hierarquiaNiveisAbaixo = this.repository.findHierarquiaNiveisAbaixo(processo.getId());

                validarMaximoNiveisHierarquia(hierarquiaNiveisAbaixo);
            }
        }
    }

    private void validarSeProcessoEstaNaMesmaHierarquia(final List<BigInteger> hierarquiaNiveis, final BigInteger idProcesso) {
        if (hierarquiaNiveis.stream().anyMatch(idProcesso::equals)) {
            throw new ResultException(Messages.PROCESSO_VINCULADO_NA_MESMA_HIERARQUIA);
        }
    }

    private void validarMaximoNiveisHierarquia(final List<BigInteger> hierarquiaNiveis) {
        if (hierarquiaNiveis.size() == Constants.MAXIMO_NIVEIS_HIERARQUIA_PROCESSO) {
            throw new ResultException(Messages.MAXIMO_NIVEIS_HIERARQUIA_PROCESSO.format(Constants.MAXIMO_NIVEIS_HIERARQUIA_PROCESSO));
        }
    }
}
