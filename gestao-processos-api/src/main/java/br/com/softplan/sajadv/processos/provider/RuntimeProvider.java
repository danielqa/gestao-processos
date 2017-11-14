package br.com.softplan.sajadv.processos.provider;

import br.com.softplan.sajadv.processos.app.Messages;
import br.com.softplan.sajadv.processos.app.Result;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeProvider implements ExceptionMapper<RuntimeException> {

    @Inject
    private Result result;

    @Override
    public Response toResponse(RuntimeException exception) {
        LoggerFactory.getLogger(RuntimeProvider.class).error("", exception);
        return result.addMessage(Messages.ERRO_INESPERADO).asResponse(Response.Status.INTERNAL_SERVER_ERROR);
    }
}
