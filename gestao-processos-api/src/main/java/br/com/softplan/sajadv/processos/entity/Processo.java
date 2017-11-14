package br.com.softplan.sajadv.processos.entity;

import br.com.softplan.sajadv.processos.app.Constants;
import br.com.softplan.sajadv.processos.enumeration.Situacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_processo_numero", columnNames = "numero"))
public class Processo extends Model<Long> {

    @NotEmpty(message = "CAMPO_OBRIGATORIO")
    @Size(max = 20, min = 20, message = "TAMANHO_NECESSARIO")
    private String numero;

    private LocalDate dataDistribuicao;

    private boolean segredoJustica;

    @Size(max = 50, message = "TAMANHO_MAXIMO")
    private String pastaFisicaCliente;

    @Size(max = 1000, message = "TAMANHO_MAXIMO")
    private String descricao;

    @NotNull(message = "CAMPO_OBRIGATORIO")
    private Situacao situacao;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "responsavel_processo",
            joinColumns = @JoinColumn(name = "id_processo"),
            inverseJoinColumns = @JoinColumn(name = "id_responsavel"),
            inverseForeignKey = @ForeignKey(name = "fk_responsavel_processo"))
    private Set<Responsavel> responsaveis;

    @ManyToOne
    @JoinColumn(name = "id_processo_pai", foreignKey = @ForeignKey(name = "fk_processo_pai"))
    private Processo processoPai;

    @AssertFalse(message = "DATA_DISTRIBUICAO_MENOR_OU_IGUAL_DATA_ATUAL")
    @JsonIgnore
    public boolean isDataDistribuicaoMenorOuIgualDataAtual() {
        return Objects.nonNull(dataDistribuicao) && dataDistribuicao.compareTo(LocalDate.now()) > 0;
    }

    @AssertFalse(message = "MINIMO_RESPONSAVEIS")
    @JsonIgnore
    public boolean isMinimoResponsaveis() {
        return getResponsaveis().isEmpty();
    }

    @AssertFalse(message = "MAXIMO_RESPONSAVEIS")
    @JsonIgnore
    public boolean isMaximoResponsaveis() {
        return getResponsaveis().size() > Constants.MAXIMO_RESPONSAVEIS_POR_PROCESSO;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDataDistribuicao() {
        return dataDistribuicao;
    }

    public void setDataDistribuicao(LocalDate dataDistribuicao) {
        this.dataDistribuicao = dataDistribuicao;
    }

    public boolean isSegredoJustica() {
        return segredoJustica;
    }

    public void setSegredoJustica(boolean segredoJustica) {
        this.segredoJustica = segredoJustica;
    }

    public String getPastaFisicaCliente() {
        return pastaFisicaCliente;
    }

    public void setPastaFisicaCliente(String pastaFisicaCliente) {
        this.pastaFisicaCliente = pastaFisicaCliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Set<Responsavel> getResponsaveis() {
        if (Objects.isNull(responsaveis)) {
            responsaveis = new HashSet<>();
        }
        return responsaveis;
    }

    public void setResponsaveis(Set<Responsavel> responsaveis) {
        this.responsaveis = responsaveis;
    }

    public Processo getProcessoPai() {
        return processoPai;
    }

    public void setProcessoPai(Processo processoPai) {
        this.processoPai = processoPai;
    }

    public String getNomeResponsaveis() {
        return getResponsaveis().stream().map(Responsavel::getNome).collect(Collectors.joining(" - "));
    }

    public String getDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("Número processo unificado: ").append(getNumero());
        sb.append(" - Data distribuição: ").append(getDataDistribuicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        sb.append(" - Situação: ").append(getSituacao().getDescricao());

        if (isSegredoJustica()) {
            sb.append(" (Segredo de justiça)");
        }

        return sb.toString();
    }
}
