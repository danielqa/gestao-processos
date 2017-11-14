package br.com.softplan.sajadv.processos.provider;

import br.com.softplan.sajadv.processos.app.Messages;
import br.com.softplan.sajadv.processos.app.Result;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BeanValidationProvider implements ExceptionMapper<ConstraintViolationException> {

    @Inject
    private Result result;

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        result.addMessage(Messages.ERRO_VALIDACAO);

        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String property = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
            Messages message = Messages.valueOf(violation.getMessage());

            if (Messages.containsKey(property)) {
                result.addMessage(message, Messages.valueOf(property).toString());
            } else {
                result.addMessage(message, StringUtils.capitalize(property));
            }
        }

        return result.asResponse(Response.Status.PRECONDITION_FAILED);
    }

}
