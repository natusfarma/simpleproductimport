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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
            nota.setcProd(obterValorString(itemNota,"cProd"));
            nota.setxProd(obterValorString(itemNota,"xProd"));
            nota.setcEAN(obterValorString(itemNota,"cEANTrib"));
            nota.setCEST(obterValorString(itemNota,"CEST"));
            nota.setcProdANVISA(obterValorString(itemNota,"cProdANVISA"));
            nota.setNCM(obterValorInt(itemNota,"NCM"));
            nota.setuCom(obterValorString(itemNota,"uCom"));
            nota.setvUnCom(obterValorBigDecimal(itemNota,"vUnCom"));

            nota.setvICMSST(obterValorBigDecimal(itemNota,"vICMSST"));
            nota.setqTrib(obterValorBigDecimal(itemNota,"qTrib"));
            nota.setvIPI(obterValorBigDecimal(itemNota,"vIPI"));
            nota.setPrecoCusto(calculoCusto(nota));
            //(<vICMSST> / <qTrib>) + <vUnCom> + (<vIPI>/<qTrib>)


            notas.add(nota);
        }
        Set<ItemNotaXml> list = new TreeSet<>();
        list.addAll(notas);
        return list.stream().collect(Collectors.toList());
    }
    private BigDecimal calculoCusto(ItemNotaXml nota){
        BigDecimal total = nota.getvUnCom().setScale(2,RoundingMode.HALF_UP);
        if(!nota.getqTrib().equals(BigDecimal.ZERO)) {
            if (!nota.getvICMSST().equals(BigDecimal.ZERO)) {
                total = total.add(nota.getvICMSST().divide(nota.getqTrib(), 2, RoundingMode.HALF_UP));
            }
            if (!nota.getvIPI().equals(BigDecimal.ZERO)) {
                total = total.add(nota.getvIPI().divide(nota.getqTrib(), 2, RoundingMode.HALF_UP));
            }
        }
        return total;
    }

    private String obterValorString(Element element,String tag){
        try{
            return element.getElementsByTagName(tag).item(0).getTextContent();
        }catch(Exception e){
            return "";
        }
    }

    private BigDecimal obterValorBigDecimal (Element element ,String tag){
        try{
            return new BigDecimal(element.getElementsByTagName(tag).item(0).getTextContent());
        }catch(Exception e){
            return new BigDecimal("0");
        }
    }

    private int obterValorInt(Element element ,String tag){
        try{
            return Integer.parseInt(element.getElementsByTagName(tag).item(0).getTextContent());
        }catch(Exception e){
            return 0;
        }
    }
    private Long obterValorLong(Element element ,String tag){
        try{
            return Long.parseLong(element.getElementsByTagName(tag).item(0).getTextContent());
        }catch(Exception e){
            return 0L;
        }
    }
    private int obterAtributoInt(Element element ,String tag){
        try{
            return Integer.parseInt(element.getAttribute(tag));
        }catch(Exception e){
            return 0;
        }
    }


}
