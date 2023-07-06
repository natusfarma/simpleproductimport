package br.com.natusfarma.spi.Simple.Product.Importer.uteis;

import br.com.natusfarma.spi.Simple.Product.Importer.models.DadosCabecalhoXml;
import br.com.natusfarma.spi.Simple.Product.Importer.models.ItemNotaXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class LeitorXml {

    private DadosCabecalhoXml getXml;
    public DadosCabecalhoXml getXml(){
        return getXml;
    }
    public DadosCabecalhoXml lerXml(Document document){
        DadosCabecalhoXml dados = new DadosCabecalhoXml();

        Element infNFe = (Element) document.getElementsByTagName("infNFe").item(0);
        Element emit = (Element) infNFe.getElementsByTagName("emit").item(0);
        NodeList det = infNFe.getElementsByTagName("det");
        dados.setItensNotaXml(obterNotas(det));

        dados.setCNPJ(obterValorString(emit,"CNPJ"));
        dados.setxNome(obterValorString(emit,"xNome"));
        dados.setnNF(obterValorInt(infNFe,"nNF"));
        //dados.setDhEmi(obterValorDate(infNFe,"DhEmi"));
        dados.setSerie(obterValorInt(infNFe,"serie"));
        this.getXml = dados;
        return dados;
    }

    private List<ItemNotaXml> obterNotas(NodeList det){
        List<ItemNotaXml> notas = new ArrayList();
        for (int i = 0; i < det.getLength(); i++) {
            Element itemNota = (Element) det.item(i);
            ItemNotaXml nota = new ItemNotaXml();

            nota.setnItem(obterAtributoInt(itemNota,"nItem"));
            nota.setcProd(obterValorInt(itemNota,"cProd"));
            nota.setxProd(obterValorString(itemNota,"xProd"));
            nota.setcEAN(obterValorString(itemNota,"cEAN"));
            nota.setCEST(obterValorString(itemNota,"CEST"));
            nota.setcProdANVISA(obterValorString(itemNota,"cProdANVISA"));
            nota.setNCM(obterValorInt(itemNota,"NCM"));
            nota.setuCom(obterValorString(itemNota,"uCom"));
            nota.setvUnCom(obterValorDouble(itemNota,"vUnCom"));
            nota.setStatus(obterStatus(nota));

            notas.add(nota);
        }
        return notas;
    }



    private String obterValorString(Element element,String tag){
        try{
            return element.getElementsByTagName(tag).item(0).getTextContent();
        }catch(Exception e){
            return "";
        }
    }

    private double obterValorDouble (Element element ,String tag){
        try{
            return Double.parseDouble(element.getElementsByTagName(tag).item(0).getTextContent());
        }catch(Exception e){
            return -1;
        }
    }

    private int obterValorInt(Element element ,String tag){
        try{
            return Integer.parseInt(element.getElementsByTagName(tag).item(0).getTextContent());
        }catch(Exception e){
            return -1;
        }
    }
    private int obterAtributoInt(Element element ,String tag){
        try{
            return Integer.parseInt(element.getAttribute(tag));
        }catch(Exception e){
            return -1;
        }
    }

    private String obterStatus(ItemNotaXml nota){
    return "";
    }
}
