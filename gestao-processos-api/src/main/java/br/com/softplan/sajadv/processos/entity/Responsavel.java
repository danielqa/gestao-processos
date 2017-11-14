package br.com.softplan.sajadv.processos.entity;

import br.com.softplan.sajadv.processos.util.Mask;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_responsavel_cpf", columnNames = "cpf"))
public class Responsavel extends Model<Long> {

    @NotEmpty(message = "CAMPO_OBRIGATORIO")
    @Size(max = 150, message = "TAMANHO_MAXIMO")
    private String nome;

    @NotEmpty(message = "CAMPO_OBRIGATORIO")
    @CPF(message = "CPF_INVALIDO")
    private String cpf;

    @ManyToMany(mappedBy = "responsaveis", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Processo> processos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Processo> getProcessos() {
        if (Objects.isNull(processos)) {
            processos = new HashSet<>();
        }
        return processos;
    }

    public void setProcessos(Set<Processo> processos) {
        this.processos = processos;
    }

    public String getCpfFormatado() {
        return Mask.cpf(cpf);
    }

    public String getNumeroProcessos() {
        return getProcessos().stream().map(Processo::getNumero).collect(Collectors.joining(" - "));
    }
}
