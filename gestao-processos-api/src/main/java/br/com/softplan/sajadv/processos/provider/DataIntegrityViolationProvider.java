package br.com.softplan.sajadv.processos.provider;

import br.com.softplan.sajadv.processos.app.Messages;
import br.com.softplan.sajadv.processos.app.Result;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataIntegrityViolationProvider implements ExceptionMapper<DataIntegrityViolationException> {

    @Inject
    private Result result;

    @Override
    public Response toResponse(DataIntegrityViolationException exception) {
        Throwable cause = exception.getCause();
        if (cause instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) cause;

            String property = cve.getConstraintName();
            if (Messages.containsKey(property)) {
                result.addMessage(Messages.valueOf(property).toString());
            }
        }

        return result.asResponse(Response.Status.PRECONDITION_FAILED);
    }

}
