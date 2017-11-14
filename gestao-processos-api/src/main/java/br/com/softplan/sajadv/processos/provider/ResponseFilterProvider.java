package br.com.softplan.sajadv.processos.provider;

import br.com.softplan.sajadv.processos.app.Result;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ResponseFilterProvider implements ContainerResponseFilter {

    @Inject
    private Result result;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        if (!responseContext.hasEntity()) {
            responseContext.setEntity(result.getEntity(), responseContext.getEntityAnnotations(), MediaType.APPLICATION_JSON_TYPE);
            responseContext.setStatus(Response.Status.OK.getStatusCode());
        }
        result.getHeaders().forEach(responseContext.getHeaders()::add);
    }
}
