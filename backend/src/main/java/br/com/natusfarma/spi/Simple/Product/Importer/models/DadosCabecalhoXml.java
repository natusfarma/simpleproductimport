package br.com.natusfarma.spi.Simple.Product.Importer.models;

import java.time.LocalDate;
import java.util.List;

public class DadosCabecalhoXml {
    private int nNF;
    private int serie;
    private LocalDate dhEmi;
    private String CNPJ;
    private String xNome;
    private List<ItemNotaXml> itensNotaXml;


    public List<ItemNotaXml> getItensNotaXml() {
        return itensNotaXml;
    }

    public void setItensNotaXml(List<ItemNotaXml> itensNotaXml) {
        this.itensNotaXml = itensNotaXml;
    }


    public int getnNF() {
        return nNF;
    }

    public void setnNF(int nNF) {
        this.nNF = nNF;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public LocalDate getDhEmi() {
        return dhEmi;
    }

    public void setDhEmi(LocalDate dhEmi) {
        this.dhEmi = dhEmi;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getxNome() {
        return xNome;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }
}
