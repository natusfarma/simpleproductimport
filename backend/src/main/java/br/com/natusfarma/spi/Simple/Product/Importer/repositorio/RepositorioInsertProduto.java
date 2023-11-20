package br.com.natusfarma.spi.Simple.Product.Importer.repositorio;

import br.com.natusfarma.spi.Simple.Product.Importer.models.DadosProduto;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.Consulta;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.FileStringUtil;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class RepositorioInsertProduto {
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private RowMapper rowMapper;
    @Autowired
    private Consulta consulta;
    private File file = new File("arquivosSql/insert-produto.sql");
    private String stringQuery = FileStringUtil.FileToString(file);

    public String insertProduto(DadosProduto dadosProduto){
        String query = stringQuery
                .replace("codigoDoProduto",String.valueOf(dadosProduto.getCodigoProduto()))
                .replace("descProd", dadosProduto.getDs_PROD())
                .replace("descUsual", dadosProduto.getDs_USUAL())
                .replace("codigoFabricante",String.valueOf(dadosProduto.getCd_FABRIC()))
                .replace("numNCM", String.valueOf(dadosProduto.getNr_NCM()))
                .replace("numCest", String.valueOf(dadosProduto.getNr_CEST()))
                .replace("codigoUsuario", String.valueOf(dadosProduto.getCd_USU()))
                .replace("compraPorSuprimento",String.valueOf(dadosProduto.getSuprimento()));
    return query;
    }

}
