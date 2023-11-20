package br.com.natusfarma.spi.Simple.Product.Importer.models;

import java.time.LocalDate;
import java.util.Date;

public class DadosProduto {
    private int codigoProduto;
    private String ds_PROD;
    private String ds_USUAL;
    private int cd_USU;
    private int cd_FABRIC;
    private int nr_NCM;
    private int nr_CEST;
    private String barras;
    private int suprimento;
    private int cd_comprador;
    private int cd_linha;
    private int cd_subCategoria;
    private int cd_categoria;
    private int cd_fornecedor;
    private String cd_produtoFornecedor;

    @Override
    public String toString() {
        return "DadosProduto{" +
                "codigoProduto=" + codigoProduto +
                ", ds_PROD='" + ds_PROD + '\'' +
                ", ds_USUAL='" + ds_USUAL + '\'' +
                ", cd_USU=" + cd_USU +
                ", cd_FABRIC=" + cd_FABRIC +
                ", nr_NCM=" + nr_NCM +
                ", nr_CEST=" + nr_CEST +
                ", barras='" + barras + '\'' +
                ", suprimento=" + suprimento +
                ", cd_comprador=" + cd_comprador +
                ", cd_linha=" + cd_linha +
                ", cd_subCategoria=" + cd_subCategoria +
                ", cd_categoria=" + cd_categoria +
                ", cd_fornecedor=" + cd_fornecedor +
                ", cd_produtoFornecedor=" + cd_produtoFornecedor +
                '}';
    }

    public String getCd_produtoFornecedor() {
        return cd_produtoFornecedor;
    }

    public void setCd_produtoFornecedor(String cd_produtoFornecedor) {
        this.cd_produtoFornecedor = cd_produtoFornecedor;
    }

    public int getCd_fornecedor() {
        return cd_fornecedor;
    }

    public void setCd_fornecedor(int cd_fornecedor) {
        this.cd_fornecedor = cd_fornecedor;
    }

    public int getCd_subCategoria() {
        return cd_subCategoria;
    }

    public void setCd_subCategoria(int cd_subCategoria) {
        this.cd_subCategoria = cd_subCategoria;
    }

    public int getCd_categoria() {
        return cd_categoria;
    }

    public void setCd_categoria(int cd_categoria) {
        this.cd_categoria = cd_categoria;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getCd_comprador() {
        return cd_comprador;
    }

    public void setCd_comprador(int cd_comprador) {
        this.cd_comprador = cd_comprador;
    }

    public int getCd_linha() {
        return cd_linha;
    }

    public void setCd_linha(int cd_linha) {
        this.cd_linha = cd_linha;
    }

    public int getSuprimento() {
        return suprimento;
    }

    public void setSuprimento(int suprimento) {
        this.suprimento = suprimento;
    }


    public String getDs_PROD() {
        return ds_PROD;
    }

    public void setDs_PROD(String ds_PROD) {
        this.ds_PROD = ds_PROD;
    }

    public String getDs_USUAL() {
        return ds_USUAL;
    }

    public void setDs_USUAL(String ds_USUAL) {
        this.ds_USUAL = ds_USUAL;
    }

    public int getCd_USU() {
        return cd_USU;
    }

    public void setCd_USU(int cd_USU) {
        this.cd_USU = cd_USU;
    }

    public int getCd_FABRIC() {
        return cd_FABRIC;
    }

    public void setCd_FABRIC(int cd_FABRIC) {
        this.cd_FABRIC = cd_FABRIC;
    }

    public int getNr_NCM() {
        return nr_NCM;
    }

    public void setNr_NCM(int nr_NCM) {
        this.nr_NCM = nr_NCM;
    }

    public int getNr_CEST() {
        return nr_CEST;
    }

    public void setNr_CEST(int nr_CEST) {
        this.nr_CEST = nr_CEST;
    }

    public String getBarras() {
        return barras;
    }

    public void setBarras(String barras) {
        this.barras = barras;
    }
}
