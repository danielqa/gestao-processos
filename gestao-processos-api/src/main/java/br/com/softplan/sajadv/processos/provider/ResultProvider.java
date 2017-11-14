package br.com.softplan.sajadv.processos.provider;

import br.com.softplan.sajadv.processos.app.Result;
import br.com.softplan.sajadv.processos.exception.ResultException;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResultProvider implements ExceptionMapper<ResultException> {

    @Inject
    private Result result;

    @Override
    public Response toResponse(ResultException exception) {
        return result.addMessage(exception.getMessage()).asResponse(Response.Status.PRECONDITION_FAILED);
    }
}
