package br.com.natusfarma.spi.Simple.Product.Importer;

import br.com.natusfarma.spi.Simple.Product.Importer.controller.XMLController;
import br.com.natusfarma.spi.Simple.Product.Importer.models.DadosCabecalhoXml;
import br.com.natusfarma.spi.Simple.Product.Importer.models.ItemNotaXml;
import br.com.natusfarma.spi.Simple.Product.Importer.models.ModeloPadrao;
import br.com.natusfarma.spi.Simple.Product.Importer.services.ConsultaXmlService;
import br.com.natusfarma.spi.Simple.Product.Importer.services.FornecedorService;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.Consulta;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.FileStringUtil;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.LeitorXml;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class SimpleProductImporterApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private RowMapper rowMapper;
	@Autowired
	private ConsultaXmlService service;
	public static void main(String[] args) {
		SpringApplication.run(SimpleProductImporterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//File fileXml = new File("C:\\Users\\victor.machado\\Downloads\\NFe31230613569870000128550010000259926150042574668914843187423750.xml");
		//InputStream is =  new FileInputStream(fileXml);

		//DadosCabecalhoXml dados = service.lerXml(is);
		//List<ItemNotaXml> teste = dados.getItensNotaXml();

		/*ItemNotaXml nota = new ItemNotaXml();
		nota.setvICMSST(new BigDecimal("30.81"));
		nota.setqTrib(new BigDecimal("12.00"));
		nota.setvIPI(new BigDecimal("0"));
		nota.setvUnCom(new BigDecimal("8.66"));
		System.out.println(nota.getvICMSST());
		System.out.println(nota.getqTrib());
		System.out.println(nota.getvIPI());
		System.out.println(nota.getvUnCom());
		String s = String.format("(%.3f / %.3f) + %.3f +(%.3f / %.3f)",nota.getvICMSST(),nota.getqTrib(),nota.getvUnCom(),nota.getvIPI(),nota.getqTrib());
		System.out.println(s);
		System.out.println(calculoCusto(nota));*/
	}


	private BigDecimal calculoCusto(ItemNotaXml nota){
		BigDecimal total = nota.getvUnCom();
		if(!nota.getqTrib().equals(BigDecimal.ZERO)){
			System.out.println("qTrib =" + nota.getqTrib());
			return total;
		}
		if(!nota.getvICMSST().equals(BigDecimal.ZERO)){
			total.add(nota.getvICMSST().divide(nota.getqTrib()));
		}
		if (!nota.getvIPI().equals(BigDecimal.ZERO)){
			total.add(nota.getvIPI().divide(nota.getqTrib()));
		}
		return total;
	}
}
