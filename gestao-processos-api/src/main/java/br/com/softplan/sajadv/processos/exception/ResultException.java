package br.com.softplan.sajadv.processos.exception;

import br.com.softplan.sajadv.processos.app.Messages;

public class ResultException extends RuntimeException {

    public ResultException(String message) {
        super(message);
    }

    public ResultException(Messages messages) {
        this(messages.toString());
    }
}
