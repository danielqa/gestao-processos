package br.com.softplan.sajadv.processos.resource;

import br.com.softplan.sajadv.processos.dto.ResponsavelFiltroDto;
import br.com.softplan.sajadv.processos.entity.Processo;
import br.com.softplan.sajadv.processos.entity.Responsavel;
import br.com.softplan.sajadv.processos.service.ResponsavelService;
import org.springframework.data.domain.Page;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.List;
import java.util.Set;

@ManagedBean
@Path("responsavel")
public class ResponsavelResource {

    @Inject
    private ResponsavelService service;

    @GET
    public List<Responsavel> findAll() {
        return this.service.findAll();
    }

    @GET
    @Path("filtros")
    public Page<Responsavel> findByFilters(@QueryParam("page") final int pageNumber, @BeanParam final ResponsavelFiltroDto dto) {
        return this.service.findByFilters(pageNumber, dto);
    }

    @GET
    @Path("{id}")
    public Responsavel get(@PathParam("id") final Long id) {
        return this.service.get(id);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") final Long id) {
        this.service.delete(id);
    }

    @POST
    public void save(@Valid final Responsavel responsavel) {
        this.service.save(responsavel);
    }

    @GET
    @Path("processos/{id}")
    public Set<Processo> getProcessos(@PathParam("id") final Long id) {
        return this.service.getProcessos(id);
    }
}
