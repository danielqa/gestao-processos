package br.com.softplan.sajadv.processos.resource;

import br.com.softplan.sajadv.processos.dto.ProcessoFiltroDto;
import br.com.softplan.sajadv.processos.entity.Processo;
import br.com.softplan.sajadv.processos.service.ProcessoService;
import org.springframework.data.domain.Page;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.List;

@ManagedBean
@Path("processo")
public class ProcessoResource {

    @Inject
    private ProcessoService service;

    @GET
    @Path("filtros")
    public Page<Processo> findByFilters(@QueryParam("page") final int pageNumber, @BeanParam final ProcessoFiltroDto dto) {
        return this.service.findByFilters(pageNumber, dto);
    }

    @GET
    @Path("vinculaveis")
    public List<Processo> findProcessosVinculaveis(@QueryParam("id") final Long id) {
        return this.service.findProcessosVinculaveis(id);
    }

    @GET
    @Path("{id}")
    public Processo get(@PathParam("id") final Long id) {
        return this.service.get(id);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") final Long id) {
        this.service.delete(id);
    }

    @POST
    public void save(@Valid final Processo processo) {
        this.service.save(processo);
    }
}
