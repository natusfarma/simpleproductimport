package br.com.natusfarma.spi.Simple.Product.Importer.uteis;

import br.com.natusfarma.spi.Simple.Product.Importer.models.DadosCabecalhoXml;
import br.com.natusfarma.spi.Simple.Product.Importer.models.ItemNotaXml;

import br.com.natusfarma.spi.Simple.Product.Importer.repositorio.RepositorioCProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class LeitorXml {

    @Autowired
    private RepositorioCProduto repositorioCProduto;

    public DadosCabecalhoXml lerXml(InputStream is) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(is);
        return obterCabecalho(document);
    }

    private DadosCabecalhoXml obterCabecalho(Document document) {
        DadosCabecalhoXml dados = new DadosCabecalhoXml();
        Element infNFe = (Element) document.getElementsByTagName("infNFe").item(0);
        Element emit = (Element) infNFe.getElementsByTagName("emit").item(0);
        NodeList det = infNFe.getElementsByTagName("det");

        dados.setCNPJ(obterValorString(emit,"CNPJ"));
        dados.setnNF(obterValorInt(infNFe,"nNF"));
        dados.setSerie(obterValorInt(infNFe,"serie"));
        dados.setItensNotaXml(obterNotas(det));
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
            nota.setcEAN(obterValorString(itemNota,"cEANTrib"));
            nota.setCEST(obterValorString(itemNota,"CEST"));
            nota.setcProdANVISA(obterValorString(itemNota,"cProdANVISA"));
            nota.setNCM(obterValorInt(itemNota,"NCM"));
            nota.setuCom(obterValorString(itemNota,"uCom"));
            nota.setvUnCom(obterValorDouble(itemNota,"vUnCom"));

            //(<vICMSST> / <qTrib>) + <vUnCom> + (<vIPI>/<qTrib>)


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


}
