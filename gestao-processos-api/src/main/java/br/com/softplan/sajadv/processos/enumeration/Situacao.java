package br.com.softplan.sajadv.processos.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Situacao {

    EM_ANDAMENTO(1, "Em andamento", false),
    DESMEMBRADO(2, "Desmembrado", false),
    EM_RECURSO(3, "Em recurso", false),
    FINALIZADO(4, "Finalizado", true),
    ARQUIVADO(5, "Arquivado", true);

    private Integer id;
    private String descricao;
    private boolean finalizado;

    Situacao(final Integer id, final String descricao, final boolean finalizado) {
        this.id = id;
        this.descricao = descricao;
        this.finalizado = finalizado;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    @JsonCreator
    public static Situacao forValue(final Integer id) {
        for (final Situacao enumeration : values()) {
            if (enumeration.getId().equals(id)) {
                return enumeration;
            }
        }
        return null;
    }
}
