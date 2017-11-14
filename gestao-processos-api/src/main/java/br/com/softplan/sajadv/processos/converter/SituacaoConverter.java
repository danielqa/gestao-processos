package br.com.softplan.sajadv.processos.converter;

import br.com.softplan.sajadv.processos.enumeration.Situacao;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class SituacaoConverter implements AttributeConverter<Situacao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Situacao attribute) {
        return Objects.nonNull(attribute) ? attribute.getId() : null;
    }

    @Override
    public Situacao convertToEntityAttribute(Integer dbData) {
        return Situacao.forValue(dbData);
    }
}
