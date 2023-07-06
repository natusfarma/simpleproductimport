package br.com.natusfarma.spi.Simple.Product.Importer.controller;

import br.com.natusfarma.spi.Simple.Product.Importer.models.DadosCabecalhoXml;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.LeitorXml;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
public class XMLController {
    private LeitorXml leitorXml = new LeitorXml();

    @PostMapping("/upload-xml")
    public DadosCabecalhoXml uploadXml(@RequestParam("file") MultipartFile file) throws IOException, ParserConfigurationException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file.getInputStream());

        DadosCabecalhoXml dados = this.leitorXml.lerXml(document);

        return dados;
    }

    @GetMapping("/getXml")
    public DadosCabecalhoXml getXml(){
        return this.leitorXml.getXml();
    }
}

