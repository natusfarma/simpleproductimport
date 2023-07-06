package br.com.natusfarma.spi.Simple.Product.Importer;

import br.com.natusfarma.spi.Simple.Product.Importer.controller.XMLController;
import br.com.natusfarma.spi.Simple.Product.Importer.models.DadosCabecalhoXml;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.LeitorXml;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

@SpringBootApplication
public class SimpleProductImporterApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SimpleProductImporterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*File file = new File("C:\\Users\\victor.machado\\Downloads\\NFe31230613569870000128550010000259926150042574668914843187423750.xml");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);
		LeitorXml ler = new LeitorXml();
		DadosCabecalhoXml dados = ler.lerXml(document);
		System.out.println(dados);*/
	}
}
