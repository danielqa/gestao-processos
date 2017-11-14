package br.com.softplan.sajadv.processos.app;

import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum Messages {

    /**
     * Validação
     */
    GRAVADO_SUCESSO,
    REMOVIDO_SUCESSO,
    ERRO_INESPERADO,
    ERRO_VALIDACAO,
    CAMPO_OBRIGATORIO,
    TAMANHO_MAXIMO,
    TAMANHO_NECESSARIO,
    MINIMO_RESPONSAVEIS,
    MAXIMO_RESPONSAVEIS,
    CPF_INVALIDO,
    PROCESSO_FINALIZADO,
    DATA_DISTRIBUICAO_MENOR_OU_IGUAL_DATA_ATUAL,
    PROCESSO_VINCULADO_NA_MESMA_HIERARQUIA,
    MAXIMO_NIVEIS_HIERARQUIA_PROCESSO,

    /**
     * Constraints
     */
    uk_responsavel_cpf,
    uk_processo_numero,
    fk_responsavel_processo,
    fk_processo_pai,

    /**
     * Labels
     */
    situacao,
    numero;

    private String message;

    Messages() {
        try {
            final String message_ = ResourceBundle.getBundle("messages").getString(name());
            this.message = new String(message_.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LoggerFactory.getLogger(Messages.class).error("", e);
        }
    }

    public static boolean containsKey(final String property) {
        return ResourceBundle.getBundle("messages").containsKey(property);
    }

    public String format(final Object... arguments) {
        return MessageFormat.format(this.message, arguments);
    }

    @Override
    public String toString() {
        return message;
    }
}
