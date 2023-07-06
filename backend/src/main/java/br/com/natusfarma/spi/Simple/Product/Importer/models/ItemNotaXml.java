package br.com.natusfarma.spi.Simple.Product.Importer.models;

public class ItemNotaXml {
    private int nItem;

    private int cProd;
    private int NCM;
    private String cEAN;
    private String xProd;
    private String CEST;
    private String uCom;
    private double vUnCom;
    private String cProdANVISA;
    private String status;
    public ItemNotaXml(){}


    public int getnItem() {
        return nItem;
    }

    public void setnItem(int nItem) {
        this.nItem = nItem;
    }

    public int getcProd() {
        return cProd;
    }

    public void setcProd(int cProd) {
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

    public double getvUnCom() {
        return vUnCom;
    }

    public void setvUnCom(double vUnCom) {
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
}
