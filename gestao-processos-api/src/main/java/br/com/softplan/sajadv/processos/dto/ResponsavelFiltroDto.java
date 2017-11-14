package br.com.softplan.sajadv.processos.dto;

import javax.ws.rs.QueryParam;

public class ResponsavelFiltroDto {

    @QueryParam("nome")
    private String nome;

    @QueryParam("cpf")
    private String cpf;

    @QueryParam("numeroProcesso")
    private String numeroProcesso;

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

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }
}
