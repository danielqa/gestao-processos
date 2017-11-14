package br.com.softplan.sajadv.processos.util;

import java.util.Objects;

public class Mask {

    public static String cpf(String cpf) {
        if (Objects.nonNull(cpf) && !cpf.isEmpty()) {
            return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        return "";
    }

}
