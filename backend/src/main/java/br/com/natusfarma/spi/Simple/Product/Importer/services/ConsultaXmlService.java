package br.com.natusfarma.spi.Simple.Product.Importer.services;

import br.com.natusfarma.spi.Simple.Product.Importer.models.DadosCabecalhoXml;
import br.com.natusfarma.spi.Simple.Product.Importer.models.ItemNotaXml;
import br.com.natusfarma.spi.Simple.Product.Importer.models.ModeloPadrao;
import br.com.natusfarma.spi.Simple.Product.Importer.repositorio.RepositorioCProduto;
import br.com.natusfarma.spi.Simple.Product.Importer.repositorio.RepositorioFornecedor;
import br.com.natusfarma.spi.Simple.Product.Importer.repositorio.RepositorioVinculoProdutoFornecedor;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Service
public class ConsultaXmlService {
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private RowMapper rowMapper;

    @Autowired
    private RepositorioCProduto repositorioCProduto;
    @Autowired
    private RepositorioVinculoProdutoFornecedor repositorioVinculoProdutoFornecedor;
    @Autowired
    private LeitorXml leitorXml;
    @Autowired
    private RepositorioFornecedor fornecedor;
    public DadosCabecalhoXml lerXml(InputStream file) throws IOException, ParserConfigurationException, SAXException {
        DadosCabecalhoXml dados = this.leitorXml.lerXml(file);

        List<ModeloPadrao> fornecedor = this.fornecedor.consultaCnpj(dados.getCNPJ());
        dados.setFornecedores(fornecedor);
        dados.setItensNotaXml(obterLista(dados));
        return dados;
    }

    private List<ItemNotaXml> obterLista(DadosCabecalhoXml dados){
        List<ItemNotaXml> itensNotaXml = dados.getItensNotaXml();
        for (int i = 0; i < itensNotaXml.size(); i++) {
            ItemNotaXml item = itensNotaXml.get(i);
            String status = "ok";
            String codigoProduto = verificarBarras(item.getcEAN());
            if(codigoProduto == ""){
                codigoProduto = verificarVinculo(item.getcProd(),dados.getFornecedores().get(0).getCodigo());
                status = "atencao";
                if (codigoProduto == ""){
                    status = "erro";
                }
            }
            item.setCodigoProduto(codigoProduto);
            item.setStatus(status);
        }
        return itensNotaXml;
    }

    public String verificarBarras(String cEAN){
        try{
            List<String> codigos = repositorioCProduto.consultaCodigo(cEAN);
            return codigos.get(0);
        }catch (Exception e){
            return "";
        }
    }

    public String verificarVinculo(Long codigoProduto,int codigoFornecedor){
        try{
            List<String> codigos = repositorioVinculoProdutoFornecedor.consultaCodigo(codigoProduto,codigoFornecedor);
           return codigos.get(0);
        }catch (Exception e){
            e.getMessage();
        }
        return "";
    }
}
