package br.com.natusfarma.spi.Simple.Product.Importer.repositorio;

import br.com.natusfarma.spi.Simple.Product.Importer.models.DadosProduto;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.Consulta;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.FileStringUtil;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.text.SimpleDateFormat;

@Repository
public class RepositorioBarras {

    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private RowMapper rowMapper;
    @Autowired
    private Consulta consulta;
    private File file = new File("arquivosSql/insert-barras.sql");
    private String stringQuery = FileStringUtil.FileToString(file);

    public String insertBarras(DadosProduto dadosProduto){
        String query = stringQuery
                .replace("codigoDoProduto",String.valueOf(dadosProduto.getCodigoProduto()))
                .replace("codigoUsuario", String.valueOf(dadosProduto.getCd_USU()))
                .replace("barras", dadosProduto.getBarras());
        return query;
    }
}
