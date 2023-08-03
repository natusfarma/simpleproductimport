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
public class RepositorioInsertComprador {
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private RowMapper rowMapper;
    @Autowired
    private Consulta consulta;
    private File file = new File("arquivosSql/insert-comprador.sql");
    private String stringQuery = FileStringUtil.FileToString(file);

    public String inserirComprador(DadosProduto dadosProduto){
        String query = stringQuery
                .replace("codigoComprador",String.valueOf(dadosProduto.getCd_comprador()));
        return query;
    }
}
