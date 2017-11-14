package br.com.softplan.sajadv.processos.dto;

import javax.ws.rs.QueryParam;
import java.time.LocalDate;

public class ProcessoFiltroDto {

    @QueryParam("numero")
    private String numero;

    @QueryParam("dataDistribuicaoInicio")
    private LocalDate dataDistribuicaoInicio;

    @QueryParam("dataDistribuicaoFim")
    private LocalDate dataDistribuicaoFim;

    @QueryParam("situacao")
    private Integer situacao;

    @QueryParam("segredoJustica")
    private boolean segredoJustica;

    @QueryParam("pastaFisicaCliente")
    private String pastaFisicaCliente;

    @QueryParam("nomeResponsavel")
    private String nomeResponsavel;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDataDistribuicaoInicio() {
        return dataDistribuicaoInicio;
    }

    public void setDataDistribuicaoInicio(LocalDate dataDistribuicaoInicio) {
        this.dataDistribuicaoInicio = dataDistribuicaoInicio;
    }

    public LocalDate getDataDistribuicaoFim() {
        return dataDistribuicaoFim;
    }

    public void setDataDistribuicaoFim(LocalDate dataDistribuicaoFim) {
        this.dataDistribuicaoFim = dataDistribuicaoFim;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
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

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }
}
