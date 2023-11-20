package br.com.natusfarma.spi.Simple.Product.Importer.models;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemNotaXml implements Comparable<ItemNotaXml>{
    private int nItem;

    private String cProd;
    private int NCM;
    private String cEAN;
    private String xProd;
    private String CEST;
    private String uCom;
    private BigDecimal vUnCom;
    private BigDecimal vICMSST;
    private BigDecimal qTrib;
    private BigDecimal vIPI;
    private String cProdANVISA;

    private String codigoProduto;
    private String status;


    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    private BigDecimal precoCusto;
    public ItemNotaXml(){}


    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getvICMSST() {
        return vICMSST;
    }

    public void setvICMSST(BigDecimal vICMSST) {
        this.vICMSST = vICMSST;
    }

    public BigDecimal getqTrib() {
        return qTrib;
    }

    public void setqTrib(BigDecimal qTrib) {
        this.qTrib = qTrib;
    }

    public BigDecimal getvIPI() {
        return vIPI;
    }

    public void setvIPI(BigDecimal vIPI) {
        this.vIPI = vIPI;
    }

    public int getnItem() {
        return nItem;
    }

    public void setnItem(int nItem) {
        this.nItem = nItem;
    }

    public String getcProd() {
        return cProd;
    }

    public void setcProd(String cProd) {
        this.cProd = cProd;
    }

    public int getNCM() {
        return NCM;
    }

    public void setNCM(int NCM) {
        this.NCM = NCM;
    }

    public String getcEAN() {
        return cEAN;
    }

    public void setcEAN(String cEAN) {
        this.cEAN = cEAN;
    }

    public String getxProd() {
        return xProd;
    }

    public void setxProd(String xProd) {
        this.xProd = xProd;
    }

    public String getCEST() {
        return CEST;
    }

    public void setCEST(String CEST) {
        this.CEST = CEST;
    }

    public String getuCom() {
        return uCom;
    }

    public void setuCom(String uCom) {
        this.uCom = uCom;
    }

    public BigDecimal getvUnCom() {
        return vUnCom;
    }

    public void setvUnCom(BigDecimal vUnCom) {
        this.vUnCom = vUnCom;
    }

    public String getcProdANVISA() {
        return cProdANVISA;
    }

    public void setcProdANVISA(String cProdANVISA) {
        this.cProdANVISA = cProdANVISA;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ItemNotaXml{" +
                "nItem=" + nItem +
                ", cProd='" + cProd + '\'' +
                ", NCM=" + NCM +
                ", cEAN='" + cEAN + '\'' +
                ", xProd='" + xProd + '\'' +
                ", CEST='" + CEST + '\'' +
                ", uCom='" + uCom + '\'' +
                ", vUnCom=" + vUnCom +
                ", vICMSST=" + vICMSST +
                ", qTrib=" + qTrib +
                ", vIPI=" + vIPI +
                ", cProdANVISA='" + cProdANVISA + '\'' +
                ", codigoProduto='" + codigoProduto + '\'' +
                ", status='" + status + '\'' +
                ", precoCusto=" + precoCusto +
                '}';
    }

    @Override
    public int compareTo(ItemNotaXml o) {
        int codigo = this.cProd.compareTo(o.cProd);
        if (codigo != 0){
            return codigo;
        }
        return 0;
    }
}
