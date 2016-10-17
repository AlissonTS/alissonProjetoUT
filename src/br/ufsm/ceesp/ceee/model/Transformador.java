package br.ufsm.ceesp.ceee.model;

/**
 * Created by Alisson on 17/10/2016.
 */
public class Transformador {

    private Long id;
    private Float kvan;
    private String localizacao;
    private Float potCTE;
    private Float impCTE;
    private Float kvar;
    private Float kw;
    private Integer clientes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Float getKvan() {
        return kvan;
    }

    public void setKvan(Float kvan) {
        this.kvan = kvan;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Float getPotCTE() {
        return potCTE;
    }

    public void setPotCTE(Float potCTE) {
        this.potCTE = potCTE;
    }

    public Float getImpCTE() {
        return impCTE;
    }

    public void setImpCTE(Float impCTE) {
        this.impCTE = impCTE;
    }

    public Float getKvar() {
        return kvar;
    }

    public void setKvar(Float kvar) {
        this.kvar = kvar;
    }

    public Float getKw() {
        return kw;
    }

    public void setKw(Float kw) {
        this.kw = kw;
    }

    public Integer getClientes() {
        return clientes;
    }

    public void setClientes(Integer clientes) {
        this.clientes = clientes;
    }
}
