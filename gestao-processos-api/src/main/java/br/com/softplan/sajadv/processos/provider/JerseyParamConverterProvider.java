package br.com.softplan.sajadv.processos.provider;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Provider
public class JerseyParamConverterProvider implements ParamConverterProvider {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] antns) {

        if (LocalDate.class == rawType) {
            return new ParamConverter<T>() {
                @Override
                public T fromString(String string) {
                    try {
                        if (Objects.nonNull(string)) {
                            LocalDate localDate = LocalDate.parse(string, formatter);
                            return rawType.cast(localDate);
                        }
                        return null;
                    } catch (Exception ex) {
                        throw new BadRequestException(ex);
                    }
                }

                @Override
                public String toString(T t) {
                    LocalDate localDate = (LocalDate) t;
                    return formatter.format(localDate);
                }
            };
        }

        return null;
    }
}